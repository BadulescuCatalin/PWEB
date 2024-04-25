package com.example.vroom.Vroom.Service;

import com.example.vroom.Vroom.Model.Office;
import com.example.vroom.Vroom.Repository.OfficeRepository;
import com.example.vroom.Vroom.config.MapperConfig;
import com.example.vroom.Vroom.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OfficeService {
    private final OfficeRepository officeRepository;
    private final MapperConfig mapperConfig;

    public List<Office> getAllOffices() {
        return officeRepository.findAll();
    }

    public Office getOfficeById(Long id) throws NotFoundException {
        Optional<Office> office = officeRepository.findById(id);
        if (office.isEmpty()) {
            throw new NotFoundException("Office not found");
        }
        return office.get();
    }

    public Office addOffice(Office office) {
        return officeRepository.save(office);
    }

    public Office updateOffice(Office office) {
        return officeRepository.save(office);
    }

    public void deleteOffice(Long id) {
        officeRepository.deleteById(id);
    }

    public List<Office> getOfficeByCity(String city) {
        return officeRepository.findByCity(city);
    }

    public List<Office> getOfficeByCountry(String country) {
        return officeRepository.findByCountry(country);
    }

    public List<Office> getOfficeByCityAndCountry(String city, String country) {
        return officeRepository.findByCityAndCountry(city, country);
    }

    public List<Office> getOfficeByAddress(String address) {
        return officeRepository.findByAddress(address);
    }


}
