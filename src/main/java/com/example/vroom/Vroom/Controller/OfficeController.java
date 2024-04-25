package com.example.vroom.Vroom.Controller;

import com.example.vroom.Vroom.Service.OfficeService;
import com.example.vroom.Vroom.dto.OfficeDTO;
import com.example.vroom.Vroom.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/office")
public class OfficeController {
    private final OfficeService officeService;

    @GetMapping("all_offices")
    public ResponseEntity<List<OfficeDTO>> getAllOffices() {
        List<OfficeDTO> offices = officeService.getAllOffices();
        if (offices != null) {
            return ResponseEntity
                    .ok()
                    .body(offices);
        }
        return ResponseEntity
                .badRequest()
                .body(null);
    }

    @GetMapping("all_offices/{id}")
    public ResponseEntity<OfficeDTO> getOfficeById(@PathVariable Long id) throws NotFoundException {
        OfficeDTO office = officeService.getOfficeById(id);
        if (office != null) {
            return ResponseEntity
                    .ok()
                    .body(office);
        }
        return ResponseEntity
                .badRequest()
                .body(null);
    }

    @GetMapping("all_offices/{city}")
    public ResponseEntity<List<OfficeDTO>> getOfficeByCity(@PathVariable String city) {
        List<OfficeDTO> offices = officeService.getOfficeByCity(city);
        if (offices != null) {
            return ResponseEntity
                    .ok()
                    .body(offices);
        }
        return ResponseEntity
                .badRequest()
                .body(null);
    }

    @GetMapping("all_offices/{city}/{country}")
    public ResponseEntity<List<OfficeDTO>> getOfficeByCityAndCountry(@PathVariable String city, @PathVariable String country) {
        List<OfficeDTO> offices = officeService.getOfficeByCityAndCountry(city, country);
        if (offices != null) {
            return ResponseEntity
                    .ok()
                    .body(offices);
        }
        return ResponseEntity
                .badRequest()
                .body(null);
    }

    @GetMapping("all_offices/{country}")
    public ResponseEntity<List<OfficeDTO>> getOfficeByCountry(@PathVariable String country) {
        List<OfficeDTO> offices = officeService.getOfficeByCountry(country);
        if (offices != null) {
            return ResponseEntity
                    .ok()
                    .body(offices);
        }
        return ResponseEntity
                .badRequest()
                .body(null);
    }

    @GetMapping("all_offices/{address}")
    public ResponseEntity<List<OfficeDTO>> getOfficeByAddress(@PathVariable String address) {
        List<OfficeDTO> offices = officeService.getOfficeByAddress(address);
        if (offices != null) {
            return ResponseEntity
                    .ok()
                    .body(offices);
        }
        return ResponseEntity
                .badRequest()
                .body(null);
    }

    @PostMapping("add_office")
    public ResponseEntity<OfficeDTO> addOffice(@RequestBody OfficeDTO office, @RequestParam Long InventoryId) throws NotFoundException {
        OfficeDTO newOffice = officeService.addOffice(office,InventoryId);
        if (newOffice != null) {
            return ResponseEntity
                    .ok()
                    .body(newOffice);
        }
        return ResponseEntity
                .badRequest()
                .body(null);
    }

    @PutMapping("update_office/{id}")
    public ResponseEntity<OfficeDTO> updateOffice(@PathVariable Long id, @RequestBody OfficeDTO office, @RequestParam(required = false) Long inventoryId)
            throws NotFoundException {
        OfficeDTO updatedOffice = officeService.updateOffice(id, office, inventoryId);
        if (updatedOffice != null) {
            return ResponseEntity
                    .ok()
                    .body(updatedOffice);
        }
        return ResponseEntity
                .badRequest()
                .body(null);
    }

    @DeleteMapping("delete_office/{id}")
    public ResponseEntity<Void> deleteOffice(@PathVariable Long id) throws NotFoundException {
        officeService.deleteOffice(id);
        return ResponseEntity
                .ok()
                .body(null);
    }

}
