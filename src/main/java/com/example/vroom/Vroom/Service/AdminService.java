package com.example.vroom.Vroom.Service;

import com.example.vroom.Vroom.Model.Admin;
import com.example.vroom.Vroom.Model.LoginRequest;
import com.example.vroom.Vroom.Repository.AdminRepository;
import com.example.vroom.Vroom.config.jwt.JwtTokenResolver;
import com.example.vroom.Vroom.dto.UserDTO;
import com.example.vroom.Vroom.dto.UserDetailsDTO;
import com.example.vroom.Vroom.exceptions.BadRequestException;
import com.example.vroom.Vroom.exceptions.NotFoundException;
import com.example.vroom.Vroom.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AdminRepository adminRepository;
    private final ModelMapper modelMapper;
    private final JwtTokenResolver jwtTokenResolver;

    public List<UserDetailsDTO> getAllAdmins() {
        List<Admin> admins = adminRepository.findAll();
        return admins.stream().map(admin -> modelMapper.map(admin, UserDetailsDTO.class)).toList();
    }

    public UserDetailsDTO getAdminById(Long id) throws NotFoundException {
        Optional<Admin> admin = adminRepository.findById(id);
        if (admin.isEmpty()) {
            throw new NotFoundException("Admin not found");
        }
        return modelMapper.map(admin.get(), UserDetailsDTO.class);
    }

    public UserDTO addAdmin(UserDTO user) {
        if (adminRepository.findByEmail(user.getEmail()) != null) {
            // SASD
            return null;
        }
        user.setPassword(Utils.hashWithKey(user.getPassword()));
        Admin adminToAdd = modelMapper.map(user, Admin.class);
        Admin addedAdmin = adminRepository.save(adminToAdd);
        return modelMapper.map(addedAdmin, UserDTO.class);
    }

    public Admin updateAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    public void deleteAdmin(Long id) {
        adminRepository.deleteById(id);
    }

    public Admin getAdminByEmail(String email) {
        return adminRepository.findByEmail(email);
    }

    public List<Admin> getAdminByLastNameAndFirstName(String lastName, String firstName) {
        return adminRepository.findByLastNameAndFirstName(lastName, firstName);
    }

    public String login(LoginRequest user) throws NotFoundException, BadRequestException {
        Admin admin = adminRepository.findByEmail(user.getEmail());
        if (admin == null) {
            throw new NotFoundException("Admin not found");
        }
        if (admin.getPassword().equals(Utils.hashWithKey(user.getPassword()))) {
            String token = jwtTokenResolver.generateJwtToken(admin.getEmail(), admin.getRole().name());
            return token;
        }
        throw new BadRequestException("Invalid credentials");
    }

}
