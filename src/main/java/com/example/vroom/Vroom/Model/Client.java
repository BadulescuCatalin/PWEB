package com.example.vroom.Vroom.Model;

import com.example.vroom.Vroom.utils.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "bookings")
public class Client implements UserWithRole{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Email
    @Column(unique = true)
    private String email;
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$",
            message = "Password must be 8 to 20 characters long, include an uppercase letter, a lowercase letter, a number, and a special character.")
    private String password;
    private String firstName;
    private Role role;
    private String lastName;
    @JsonIgnore
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Booking> bookings;

    public Role getRole() {
        return Role.ROLE_CLIENT;
    }
}
