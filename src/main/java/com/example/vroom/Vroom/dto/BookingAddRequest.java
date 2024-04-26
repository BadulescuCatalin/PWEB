package com.example.vroom.Vroom.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingAddRequest {

    private int numberOfAdditionalDrivers;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long carId;
    private Long clientId;
    private Integer numberOfGps;
    private Integer numberOfBabySeats;
    private Integer numberOfExtraBaggage;
}
