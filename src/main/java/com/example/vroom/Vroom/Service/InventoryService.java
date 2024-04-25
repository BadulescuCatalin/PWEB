package com.example.vroom.Vroom.Service;

import com.example.vroom.Vroom.Model.Inventory;
import com.example.vroom.Vroom.Repository.InventoryRepository;
import com.example.vroom.Vroom.config.MapperConfig;
import com.example.vroom.Vroom.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InventoryService {
    private final InventoryRepository inventoryRepository;
    private final MapperConfig mapperConfig;
    public List<Inventory> getAllInventory() {
        return inventoryRepository.findAll();
    }

    public Inventory getInventoryById(Long id) throws NotFoundException {
        Optional<Inventory> inventory = inventoryRepository.findById(id);
        if (inventory.isEmpty()) {
            throw new NotFoundException("Inventory not found");
        }
        return inventory.get();
    }

}
