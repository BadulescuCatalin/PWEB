package com.example.vroom.Vroom.Controller;

import com.example.vroom.Vroom.Model.LoginRequest;
import com.example.vroom.Vroom.Service.AdminService;
import com.example.vroom.Vroom.Service.ClientService;
import com.example.vroom.Vroom.dto.UserDTO;
import com.example.vroom.Vroom.exceptions.BadRequestException;
import com.example.vroom.Vroom.exceptions.NotFoundException;
import com.example.vroom.Vroom.utils.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AdminService adminService;
    private final ClientService clientService;

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody UserDTO user) {
        // register a new user
        if (user.getRole().equals(Role.ROLE_ADMIN))
            return ResponseEntity.ok(adminService.addAdmin(user));
        else
            return ResponseEntity.ok(clientService.addClient(user));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest user) throws NotFoundException, BadRequestException {
        // login a user
        String token;
        if (user.getRole().equals(Role.ROLE_ADMIN.name())) {
            token = adminService.login(user);
        }
        else {
            token = clientService.login(user);
        }
        return ResponseEntity.ok(token);
    }
}
