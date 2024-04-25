package com.example.vroom.Vroom.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingDTO {

    private int numberOfAdditionalDrivers;
    private Double price;
    private LocalDate startDate;
    private LocalDate endDate;
}
