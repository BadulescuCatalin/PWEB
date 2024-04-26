package com.example.vroom.Vroom.Controller;

import com.example.vroom.Vroom.Service.BookingService;
import com.example.vroom.Vroom.dto.BookingAddRequest;
import com.example.vroom.Vroom.dto.BookingDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/booking")
public class BookingController {
    private final BookingService bookingService;

    @GetMapping("all_bookings")
    public ResponseEntity<List<BookingDTO>> getAllBookings() {
        List<BookingDTO> bookings = bookingService.getAllBookings();
        if (bookings != null) {
            return ResponseEntity
                    .ok()
                    .body(bookings);
        }
        return ResponseEntity
                .badRequest()
                .body(null);
    }

    @GetMapping("booking/{id}")
    public ResponseEntity<BookingDTO> getBookingById(Long id) {
        try {
            BookingDTO booking = bookingService.getBookingById(id);
            return ResponseEntity
                    .ok()
                    .body(booking);
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body(null);
        }
    }

    @PutMapping("update_booking/{id}")
    public ResponseEntity<BookingDTO> updateBooking(Long id, BookingDTO booking) {
        try {
            BookingDTO updatedBooking = bookingService.updateBooking(booking, id);
            return ResponseEntity
                    .ok()
                    .body(updatedBooking);
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body(null);
        }
    }

    @DeleteMapping("delete_booking/{id}")
    public ResponseEntity<Void> deleteBooking(Long id) {
        try {
            bookingService.deleteBooking(id);
            return ResponseEntity
                    .ok()
                    .body(null);
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body(null);
        }
    }

    @GetMapping("booking_by_client/{id}")
    public ResponseEntity<List<BookingDTO>> getBookingByClientId(Long id) {
        try {
            List<BookingDTO> bookings = bookingService.getBookingByClientId(id);
            return ResponseEntity
                    .ok()
                    .body(bookings);
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body(null);
        }
    }

    @GetMapping("booking_by_car/{id}")
    public ResponseEntity<List<BookingDTO>> getBookingByCarId(Long id) {
        try {
            List<BookingDTO> bookings = bookingService.getBookingByCarId(id);
            return ResponseEntity
                    .ok()
                    .body(bookings);
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body(null);
        }
    }

    @GetMapping("booking_by_feedback/{id}")
    public ResponseEntity<BookingDTO> getBookingByFeedbackId(Long id) {
        try {
            BookingDTO booking = bookingService.getBookingByFeedbackId(id);
            return ResponseEntity
                    .ok()
                    .body(booking);
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body(null);
        }
    }

}
