package com.example.vroom.Vroom.Controller;

import com.example.vroom.Vroom.Service.BookingService;
import com.example.vroom.Vroom.Service.ClientService;
import com.example.vroom.Vroom.dto.BookingAddRequest;
import com.example.vroom.Vroom.dto.BookingDTO;
import com.example.vroom.Vroom.dto.UserRequestResponse;
import com.example.vroom.Vroom.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/client")
public class ClientController {
    private final ClientService clientService;
    private final BookingService bookingService;

    @PutMapping("/update/{id}")
    public ResponseEntity<UserRequestResponse> updateClient(@PathVariable Long id, @RequestBody UserRequestResponse client) throws NotFoundException {
        UserRequestResponse userRequestResponse = clientService.updateClient(client, id);
        if (userRequestResponse != null) {
            return ResponseEntity
                    .ok()
                    .body(userRequestResponse);
        }
        return ResponseEntity
                .badRequest()
                .body(null);
    }

    @GetMapping("/client/{id}")
    public ResponseEntity<UserRequestResponse> getClientById(@PathVariable Long id) throws NotFoundException {
        UserRequestResponse userRequestResponse = clientService.getClientById(id);
        if (userRequestResponse != null) {
            return ResponseEntity
                    .ok()
                    .body(userRequestResponse);
        }
        return ResponseEntity
                .badRequest()
                .body(null);
    }

    @PostMapping("add_booking")
    public ResponseEntity<BookingDTO> addBooking(BookingAddRequest booking) {
        try {
            BookingDTO newBooking = bookingService.addBooking(booking);
            return ResponseEntity
                    .ok()
                    .body(newBooking);
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body(null);
        }
    }

}
