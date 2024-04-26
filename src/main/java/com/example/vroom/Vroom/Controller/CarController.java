package com.example.vroom.Vroom.Controller;

import com.example.vroom.Vroom.Service.CarService;
import com.example.vroom.Vroom.dto.CarDTO;
import com.example.vroom.Vroom.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/car")
public class CarController {
    private final CarService carService;
    @GetMapping("/allCars")
    public ResponseEntity<List<CarDTO>> getAllCars() {
        List<CarDTO> userRequest = carService.getAllCars();
        if (userRequest != null) {
            return ResponseEntity
                    .ok()
                    .body(userRequest);
        }
        return ResponseEntity
                .badRequest()
                .body(null);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarDTO> getCarById(@PathVariable Long id) throws NotFoundException {
        CarDTO carDTO = carService.getCarById(id);
        if (carDTO != null) {
            return ResponseEntity
                    .ok()
                    .body(carDTO);
        }
        return ResponseEntity
                .badRequest()
                .body(null);
    }

    @GetMapping("/fuelType/{fuelType}")
    public ResponseEntity<List<CarDTO>> getCarByFuelType(@PathVariable String fuelType) {
        List<CarDTO> carDTO = carService.getCarByFuelType(fuelType);
        if (carDTO != null) {
            return ResponseEntity
                    .ok()
                    .body(carDTO);
        }
        return ResponseEntity
                .badRequest()
                .body(null);
    }

    @GetMapping("/transmission/{transmission}")
    public ResponseEntity<List<CarDTO>> getCarByTransmission(@PathVariable String transmission) {
        List<CarDTO> carDTO = carService.getCarByTransmission(transmission);
        if (carDTO != null) {
            return ResponseEntity
                    .ok()
                    .body(carDTO);
        }
        return ResponseEntity
                .badRequest()
                .body(null);
    }

    @GetMapping("/numberOfSeats/{numberOfSeats}")
    public ResponseEntity<List<CarDTO>> getCarsWithMinimumNumberOfSeats(@PathVariable Integer numberOfSeats) {
        List<CarDTO> carDTO = carService.getCarsWithMinimumNumberOfSeats(numberOfSeats);
        if (carDTO != null) {
            return ResponseEntity
                    .ok()
                    .body(carDTO);
        }
        return ResponseEntity
                .badRequest()
                .body(null);
    }

    @GetMapping("/pricePerDayMax/{pricePerDay}")
    public ResponseEntity<List<CarDTO>> getCarsWithMaximumPricePerDay(@PathVariable Integer pricePerDay) {
        List<CarDTO> carDTO = carService.getCarsWithMaximumPricePerDay(pricePerDay);
        if (carDTO != null) {
            return ResponseEntity
                    .ok()
                    .body(carDTO);
        }
        return ResponseEntity
                .badRequest()
                .body(null);
    }
    @GetMapping("/pricePerDayAsc")
    public ResponseEntity<List<CarDTO>> getCarsSortedByPricePerDayAsc() {
        List<CarDTO> carDTO = carService.getCarsSortedByPricePerDayAsc();
        if (carDTO != null) {
            return ResponseEntity
                    .ok()
                    .body(carDTO);
        }
        return ResponseEntity
                .badRequest()
                .body(null);
    }

    @GetMapping("/ratingMin/{rating}")
    public ResponseEntity<List<CarDTO>> getCarsWithMinimumRating(@PathVariable Double rating) {
        List<CarDTO> carDTO = carService.getCarsWithMinimumRating(rating);
        if (carDTO != null) {
            return ResponseEntity
                    .ok()
                    .body(carDTO);
        }
        return ResponseEntity
                .badRequest()
                .body(null);
    }

    @GetMapping("/city/{city}")
    public ResponseEntity<List<CarDTO>> getCarsInCity(@PathVariable String city) {
        List<CarDTO> carDTO = carService.getCarsInCity(city);
        if (carDTO != null) {
            return ResponseEntity
                    .ok()
                    .body(carDTO);
        }
        return ResponseEntity
                .badRequest()
                .body(null);
    }

    @GetMapping("/office/{officeId}")
    public ResponseEntity<List<CarDTO>> getCarsByOfficeId(@PathVariable Long officeId) {
        List<CarDTO> carDTO = carService.getCarsByOfficeId(officeId);
        if (carDTO != null) {
            return ResponseEntity
                    .ok()
                    .body(carDTO);
        }
        return ResponseEntity
                .badRequest()
                .body(null);
    }

}
