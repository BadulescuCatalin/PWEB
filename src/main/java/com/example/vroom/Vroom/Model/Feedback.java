package com.example.vroom.Vroom.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"booking"})
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String message;
    private Double rating;
    @JsonIgnore
    @OneToOne(mappedBy = "feedback")
    private Booking booking;
}
