package com.example.vroom.Vroom.config;

import com.example.vroom.Vroom.utils.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Locale;

@Configuration
@EnableWebSecurity
public class SecurityFilterConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http

        .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests.requestMatchers(
                                "/auth/**",
                                "/swagger-ui/**",
                                "/v2/**",
                                "/webjars/**",
                                "/v3/**",
                                "/**"
                                )
                                .permitAll()
//                                .requestMatchers("/admin/**").hasAnyAuthority(Role.ROLE_ADMIN.name())
//                                .requestMatchers("/client/**").hasAnyAuthority(Role.ROLE_CLIENT.name())
                                .anyRequest().authenticated()
                );


        return http.build();
    }

}
