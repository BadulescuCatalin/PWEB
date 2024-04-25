package com.example.vroom.Vroom.Repository;

import com.example.vroom.Vroom.Model.Office;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OfficeRepository extends JpaRepository<Office, Long> {
    List<Office> findByCity(String city);

    List<Office> findByCountry(String country);

    List<Office> findByCityAndCountry(String city, String country);

    List<Office> findByAddress(String address);
    Optional<Office> findByInventoryId(Long id);
}
