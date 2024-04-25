package com.example.vroom.Vroom.config.jwt;

import com.example.vroom.Vroom.Model.Admin;
import com.example.vroom.Vroom.Model.Client;
import com.example.vroom.Vroom.Repository.AdminRepository;
import com.example.vroom.Vroom.Repository.ClientRepository;
import com.example.vroom.Vroom.exceptions.ExpiredTokenException;
import com.example.vroom.Vroom.exceptions.InvalidTokenException;
import com.example.vroom.Vroom.exceptions.NotFoundException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private final ClientRepository clientRepository;
    private final AdminRepository adminRepository;
    private final JwtTokenResolver jwtTokenResolver;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain)
            throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        if (requestURI.startsWith("/swagger-ui/") || requestURI.startsWith("/v2/api-docs") ||
                requestURI.startsWith("/webjars/") || requestURI.startsWith("/swagger-resources")
                || requestURI.startsWith("/csrf") || requestURI.startsWith("/v3/api-docs") ||
                requestURI.startsWith("/auth")
        ) {
            filterChain.doFilter(request, response);
            return;
        }
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Bearer token not present");
            return;
        }
        String jwtToken = authHeader.substring(7);
        String role = jwtTokenResolver.getRoleFromToken(jwtToken);
        String email = jwtTokenResolver.getEmailFromToken(jwtToken);
        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(role));
        Authentication authentication = new UsernamePasswordAuthenticationToken(email, null, authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        if ("ROLE_CLIENT".equals(role)) {
            Client client = clientRepository.findByEmail(email);
            if (client == null) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "No client found for bearer token");
                return;
            }
            try {
                jwtTokenResolver.validateToken(jwtToken,
                        client.getEmail(), role);
            } catch (InvalidTokenException | ExpiredTokenException e) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
                return;
            }
        } else if ("ROLE_ADMIN".equals(role)) {
            Admin admin = adminRepository.findByEmail(email);
            if (admin == null) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "No admin found for bearer token");
                return;
            }
            try {
                jwtTokenResolver.validateToken(jwtToken,
                        admin.getEmail(), role);
            } catch (InvalidTokenException | ExpiredTokenException e) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
                return;
            }
        } else {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid role in bearer token");
            return;
        }
        if (requestURI.startsWith("/admin") && !"ROLE_ADMIN".equals(role)) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid role in bearer token");
        }
        if (requestURI.startsWith("/client") && !"ROLE_CLIENT".equals(role)) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid role in bearer token");
        }
        filterChain.doFilter(request, response);
    }
}
