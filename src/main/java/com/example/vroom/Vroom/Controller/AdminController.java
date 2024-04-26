package com.example.vroom.Vroom.Controller;

import com.example.vroom.Vroom.Service.AdminService;
import com.example.vroom.Vroom.Service.CarService;
import com.example.vroom.Vroom.Service.ClientService;
import com.example.vroom.Vroom.dto.CarDTO;
import com.example.vroom.Vroom.dto.UserRequestResponse;
import com.example.vroom.Vroom.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;
    private final ClientService clientService;
    private final CarService carService;

    @GetMapping("/allClients")
    public ResponseEntity<List<UserRequestResponse>> getAllClients() {
        List<UserRequestResponse> userRequestResponse = clientService.getAllClients();
        if (userRequestResponse != null) {
            return ResponseEntity
                    .ok()
                    .body(userRequestResponse);
        }
        return ResponseEntity
                .badRequest()
                .body(null);
    }

    @PostMapping("/addCar")
    public ResponseEntity<CarDTO> addCar(@RequestBody CarDTO carDTO, @RequestParam Long officeId) throws NotFoundException {
        CarDTO car = carService.addCar(carDTO, officeId);
        if (car != null) {
            return ResponseEntity
                    .ok()
                    .body(car);
        }
        return ResponseEntity
                .badRequest()
                .body(null);
    }

    @PostMapping("/updateCar/{id}")
    public ResponseEntity<CarDTO> updateCar(CarDTO carDTO, Long id) throws NotFoundException {
        CarDTO car = carService.updateCar(carDTO, id);
        if (car != null) {
            return ResponseEntity
                    .ok()
                    .body(car);
        }
        return ResponseEntity
                .badRequest()
                .body(null);
    }

    @DeleteMapping("/deleteCar/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long id) throws NotFoundException {
        carService.deleteCar(id);
        return ResponseEntity
                .ok()
                .body(null);
    }




}
