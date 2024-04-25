package com.example.vroom.Vroom.Service;

import com.example.vroom.Vroom.Model.Inventory;
import com.example.vroom.Vroom.Model.Office;
import com.example.vroom.Vroom.Repository.InventoryRepository;
import com.example.vroom.Vroom.Repository.OfficeRepository;
import com.example.vroom.Vroom.config.MapperConfig;
import com.example.vroom.Vroom.dto.InventoryDTO;
import com.example.vroom.Vroom.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.Arrays.stream;

@Service
@RequiredArgsConstructor
public class InventoryService {
    private final InventoryRepository inventoryRepository;
    private final ModelMapper modelMapper;
    private final OfficeRepository officeRepository;
    public List<InventoryDTO> getAllInventory() {
        List<Inventory> inventory = inventoryRepository.findAll();
        return inventory.stream().map(inv -> modelMapper.map(inv, InventoryDTO.class)).toList();
    }

    public InventoryDTO getInventoryById(Long id) throws NotFoundException {
        Optional<Inventory> inventory = inventoryRepository.findById(id);
        if (inventory.isEmpty()) {
            throw new NotFoundException("Inventory not found");
        }
        return modelMapper.map(inventory.get(), InventoryDTO.class);
    }

    public InventoryDTO createInventory(InventoryDTO inventory) {
        Inventory newInventory = modelMapper.map(inventory, Inventory.class);
        return modelMapper.map(inventoryRepository.save(newInventory), InventoryDTO.class);
    }

    public InventoryDTO updateInventory(Long id, InventoryDTO inventory) throws NotFoundException {
        Optional<Inventory> inventoryOptional = inventoryRepository.findById(id);
        if (inventoryOptional.isEmpty()) {
            throw new NotFoundException("Inventory not found");
        }
        Inventory inventoryToUpdate = inventoryOptional.get();
        inventoryToUpdate.setNumberOfGps(inventory.getNumberOfGps());
        inventoryToUpdate.setNumberOfBabySeats(inventory.getNumberOfBabySeats());
        inventoryToUpdate.setNumberOfExtraBaggage(inventory.getNumberOfExtraBaggage());
        return modelMapper.map(inventoryRepository.save(inventoryToUpdate), InventoryDTO.class);
    }

    public void deleteInventory(Long id) throws NotFoundException {
        Optional<Inventory> inventory = inventoryRepository.findById(id);
        if (inventory.isEmpty()) {
            throw new NotFoundException("Inventory not found");
        }
        Optional<Office> office = officeRepository.findByInventoryId(inventory.get().getId());
        if (office.isPresent()) {
            office.get().setInventory(null);
            officeRepository.save(office.get());
        }
        inventoryRepository.deleteById(id);
    }


}
