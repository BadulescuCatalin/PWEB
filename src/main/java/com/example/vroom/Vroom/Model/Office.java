package com.example.vroom.Vroom.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"admins", "cars", "inventory"})
public class Office {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String address;
    private String city;
    private String country;
    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "inventory_id", referencedColumnName = "id")
    private Inventory inventory;
    @JsonIgnore
    @OneToMany(mappedBy = "office")
    private Set<Admin> admins;
    @JsonIgnore
    @OneToMany(mappedBy = "office")
    private Set<Car> cars;
}
