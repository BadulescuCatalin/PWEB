package com.example.vroom.Vroom.Controller;

import com.example.vroom.Vroom.Model.Inventory;
import com.example.vroom.Vroom.Service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/inventory")
public class InventoryController {
    private final InventoryService inventoryService;

    @GetMapping("all_inventory")
    public ResponseEntity<List<Inventory>> getAllInventory() {
        List<Inventory> inventory = inventoryService.getAllInventory();
        if (inventory != null) {
            return ResponseEntity
                    .ok()
                    .body(inventory);
        }
        return ResponseEntity
                .badRequest()
                .body(null);
    }



}
