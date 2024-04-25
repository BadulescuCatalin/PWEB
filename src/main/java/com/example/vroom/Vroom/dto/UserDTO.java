package com.example.vroom.Vroom.dto;

import com.example.vroom.Vroom.utils.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    @Email
    private String email;
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$",
            message = "Password must be 8 to 20 characters long, include an uppercase letter, a lowercase letter, a number, and a special character.")
    private String password;
    @Pattern(regexp = "^[a-zA-Z]*$", message = "First name must only contain alphabetic characters.")
    private String firstName;
    @Pattern(regexp = "^[a-zA-Z]*$", message = "First name must only contain alphabetic characters.")
    private String lastName;
    private Role role;
}
