package com.example.vroom.Vroom.dto;

import com.example.vroom.Vroom.Model.Office;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventoryDTO {
    private Integer numberOfGps;
    private Integer numberOfBabySeats;
    private Integer numberOfExtraBaggage;
}
