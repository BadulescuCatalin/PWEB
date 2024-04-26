package com.example.vroom.Vroom.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestResponse {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
}
