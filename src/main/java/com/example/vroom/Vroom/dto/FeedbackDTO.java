package com.example.vroom.Vroom.dto;

import com.example.vroom.Vroom.Model.Booking;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackDTO {
    private String message;
    private Double rating;
}
