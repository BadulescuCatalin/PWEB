package com.example.vroom.Vroom.Model;

import com.example.vroom.Vroom.utils.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "office")
public class Admin implements UserWithRole{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(unique = true)
    private String email;
    private String password;
    private String firstName;
    private String lastName;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "office_id")
    private Office office;

    @Override
    public Role getRole() {
        return Role.ROLE_ADMIN;
    }


}
