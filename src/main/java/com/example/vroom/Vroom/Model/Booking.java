package com.example.vroom.Vroom.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"feedback", "car", "client"})
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private int numberOfAdditionalDrivers;
    private Double price;
    private LocalDate startDate;
    private LocalDate endDate;
    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "feedback_id", referencedColumnName = "id")
    private Feedback feedback;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "car_id", referencedColumnName = "id")
    private Car car;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Client client;
}
