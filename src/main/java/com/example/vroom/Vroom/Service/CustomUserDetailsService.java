package com.example.vroom.Vroom.Service;

import com.example.vroom.Vroom.Model.UserWithRole;
import com.example.vroom.Vroom.Repository.AdminRepository;
import com.example.vroom.Vroom.Repository.ClientRepository;
import com.example.vroom.Vroom.dto.UserDetailsDTO;
import com.example.vroom.Vroom.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final AdminRepository adminRepository;
    private final ClientRepository clientRepository;
    private final ModelMapper modelMapper;

    @Override
    public UserDetails loadUserByUsername(String email) {
        UserWithRole user = adminRepository.findByEmail(email);
        UserDetailsDTO userDto = modelMapper.map(user, UserDetailsDTO.class);
        if (user == null) {
            user = (UserWithRole) clientRepository.findByEmail(email);
            userDto = modelMapper.map(user, UserDetailsDTO.class);
        }
        if (user == null) {
            try {
                throw new NotFoundException("User not found with email:)" + email);
            } catch (NotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole().name()));

        return new org.springframework.security.core.userdetails.User(userDto.getEmail(), userDto.getPassword(), grantedAuthorities);
    }
}
