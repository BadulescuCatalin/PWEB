package com.example.vroom.Vroom.Repository;

import com.example.vroom.Vroom.Model.Admin;
import com.example.vroom.Vroom.Model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findByFuelType(String fuelType);
    List<Car> findByTransmission(String transmission);
//    @Query("SELECT c FROM Car c WHERE c.number_of_seats >= :numberOfSeats")
    List<Car> findByNumberOfSeatsGreaterThanEqual(Integer numberOfSeats);

//    @Query("SELECT c FROM Car c WHERE c.price_per_day <= :pricePerDay")
    List<Car> findByPricePerDayLessThanEqual(Integer pricePerDay);
    List<Car> findByOrderByPricePerDayAsc();
    List<Car> findByRatingGreaterThanEqual(Double rating);
    List<Car> findByOfficeId(Long officeId);
    @Query(nativeQuery = true, value ="SELECT c FROM car c JOIN office o ON c.office_id = o.id WHERE o.city = :city")
    List<Car> getCarsInCity(String city);

}
