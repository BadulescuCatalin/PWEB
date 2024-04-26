package com.example.vroom.Vroom.Service;

import com.example.vroom.Vroom.Model.Admin;
import com.example.vroom.Vroom.Model.Inventory;
import com.example.vroom.Vroom.Model.Office;
import com.example.vroom.Vroom.Repository.AdminRepository;
import com.example.vroom.Vroom.Repository.InventoryRepository;
import com.example.vroom.Vroom.Repository.OfficeRepository;
import com.example.vroom.Vroom.dto.OfficeDTO;
import com.example.vroom.Vroom.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OfficeService {
    private final OfficeRepository officeRepository;
    private final ModelMapper modelMapper;
    private final InventoryRepository inventoryRepository;
    private final AdminRepository adminRepository;

    public List<OfficeDTO> getAllOffices() {
        List<Office> offices = officeRepository.findAll();
        return offices.stream().map(office -> modelMapper.map(office, OfficeDTO.class)).toList();
    }

    public OfficeDTO getOfficeById(Long id) throws NotFoundException {
        Optional<Office> office = officeRepository.findById(id);
        if (office.isEmpty()) {
            throw new NotFoundException("Office not found");
        }
        return modelMapper.map(office.get(), OfficeDTO.class);
    }

    public OfficeDTO addOffice(OfficeDTO office, Long InventoryId) throws NotFoundException {
        Optional<Inventory> inventory = inventoryRepository.findById(InventoryId);
        if (inventory.isEmpty()) {
            throw new NotFoundException("Inventory not found");
        }
        Office officeToAdd = modelMapper.map(office, Office.class);
        officeToAdd.setInventory(inventory.get());
        Office addedOffice = officeRepository.save(officeToAdd);
        return modelMapper.map(addedOffice, OfficeDTO.class);
    }

    public OfficeDTO updateOffice(Long id, OfficeDTO office, Long inventoryId) throws NotFoundException {
        Optional<Office> officeOptional = officeRepository.findById(id);
        if (officeOptional.isEmpty()) {
            throw new NotFoundException("Office not found");
        }
        Office officeToUpdate = officeOptional.get();
        officeToUpdate.setCity(office.getCity());
        officeToUpdate.setCountry(office.getCountry());
        officeToUpdate.setAddress(office.getAddress());
        if (inventoryId != null) {
            Optional<Inventory> inventory = inventoryRepository.findById(inventoryId);
            if (inventory.isEmpty()) {
                throw new NotFoundException("Inventory not found");
            }
            officeToUpdate.setInventory(inventory.get());
        }
        return modelMapper.map(officeRepository.save(officeToUpdate), OfficeDTO.class);
    }

    public void deleteOffice(Long id) throws NotFoundException {
        Optional<Office> office = officeRepository.findById(id);
        if (office.isEmpty()) {
            throw new NotFoundException("Office not found");
        }
        inventoryRepository.delete(office.get().getInventory());
        List<Admin> admins = adminRepository.findByOfficeId(office.get().getId());
        for (Admin admin : admins) {
            admin.setOffice(null);
            adminRepository.save(admin);
        }
        officeRepository.deleteById(id);
    }

    public List<OfficeDTO> getOfficeByCity(String city) {
        List<Office> offices = officeRepository.findByCity(city);
        return offices.stream().map(office -> modelMapper.map(office, OfficeDTO.class)).toList();
    }

    public List<OfficeDTO> getOfficeByCountry(String country) {
        List<Office> offices = officeRepository.findByCountry(country);
        return offices.stream().map(office -> modelMapper.map(office, OfficeDTO.class)).toList();
    }

    public List<OfficeDTO> getOfficeByCityAndCountry(String city, String country) {
        List<Office> offices = officeRepository.findByCityAndCountry(city, country);
        return offices.stream().map(office -> modelMapper.map(office, OfficeDTO.class)).toList();
    }

    public List<OfficeDTO> getOfficeByAddress(String address) {
        List<Office> offices = officeRepository.findByAddress(address);
        return offices.stream().map(office -> modelMapper.map(office, OfficeDTO.class)).toList();
    }

}
