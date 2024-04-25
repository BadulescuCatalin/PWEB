package com.example.vroom.Vroom.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"office"})
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private Integer numberOfGps;
    private Integer numberOfBabySeats;
    private Integer numberOfExtraBaggage;
    @JsonIgnore
    @OneToOne(mappedBy = "inventory")
    private Office office;
}
