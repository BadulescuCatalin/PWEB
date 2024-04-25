package com.example.vroom.Vroom.Controller;

import com.example.vroom.Vroom.Service.InventoryService;
import com.example.vroom.Vroom.dto.InventoryDTO;
import com.example.vroom.Vroom.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/inventory")
public class InventoryController {
    private final InventoryService inventoryService;


    @GetMapping("all_inventory")
    public ResponseEntity<List<InventoryDTO>> getAllInventory() {
        List<InventoryDTO> inventory = inventoryService.getAllInventory();
        if (inventory != null) {
            return ResponseEntity
                    .ok()
                    .body(inventory);
        }
        return ResponseEntity
                .badRequest()
                .body(null);
    }

    @GetMapping("all_inventory/{id}")
    public ResponseEntity<InventoryDTO> getInventoryById(@PathVariable Long id) throws NotFoundException {
        InventoryDTO inventory = inventoryService.getInventoryById(id);
        if (inventory != null) {
            return ResponseEntity
                    .ok()
                    .body(inventory);
        }
        return ResponseEntity
                .badRequest()
                .body(null);
    }

    @PostMapping("create_inventory")
    public ResponseEntity<InventoryDTO> createInventory(@RequestBody InventoryDTO inventory) {
        InventoryDTO newInventory = inventoryService.createInventory(inventory);
        if (newInventory != null) {
            return ResponseEntity
                    .ok()
                    .body(newInventory);
        }
        return ResponseEntity
                .badRequest()
                .body(null);
    }

    @PutMapping("update_inventory/{id}")
    public ResponseEntity<InventoryDTO> updateInventory(@PathVariable Long id, @RequestBody InventoryDTO inventory)
            throws NotFoundException {
        InventoryDTO updatedInventory = inventoryService.updateInventory(id, inventory);
        if (updatedInventory != null) {
            return ResponseEntity
                    .ok()
                    .body(updatedInventory);
        }
        return ResponseEntity
                .badRequest()
                .body(null);
    }

    @DeleteMapping("delete_inventory/{id}")
    public ResponseEntity<Void> deleteInventory(@PathVariable Long id) throws NotFoundException {
        inventoryService.deleteInventory(id);
        return ResponseEntity
                .ok()
                .build();
    }

}