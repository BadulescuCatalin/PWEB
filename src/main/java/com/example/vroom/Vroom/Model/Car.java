package com.example.vroom.Vroom.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"bookings", "office"})
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private Integer numberOfSeats;
    private Integer numberOfDoors;
    private String fuelType;
    private Double rating;
    private String transmission;
    private String description;
    private Double pricePerDay;
    @JsonIgnore
    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Booking> bookings;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "office_id")
    private Office office;
}
