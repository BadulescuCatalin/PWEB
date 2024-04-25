package com.example.vroom.Vroom.dto;

import com.example.vroom.Vroom.Model.Booking;
import com.example.vroom.Vroom.Model.Office;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarDTO {
    private Integer numberOfSeats;
    private Integer numberOfDoors;
    private String fuelType;
    private Double rating;
    private String transmission;
    private String description;
    private Double pricePerDay;
}
