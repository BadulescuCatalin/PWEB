package com.example.vroom.Vroom.dto;

import com.example.vroom.Vroom.Model.Admin;
import com.example.vroom.Vroom.Model.Car;
import com.example.vroom.Vroom.Model.Inventory;
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
public class OfficeDTO {
    private String address;
    private String city;
    private String country;
}
