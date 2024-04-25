package com.example.vroom.Vroom.Service;

import com.example.vroom.Vroom.Model.Car;
import com.example.vroom.Vroom.Repository.BookingRepository;
import com.example.vroom.Vroom.Repository.CarRepository;
import com.example.vroom.Vroom.config.MapperConfig;
import com.example.vroom.Vroom.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarService {
    // de facut verificari daca exista orasu cu id si chestii
    private final CarRepository carRepository;
    private final MapperConfig mapperConfig;
    private final BookingRepository bookingRepository;

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public Car getCarById(Long id) throws NotFoundException {
        Optional<Car> car = carRepository.findById(id);
        if (car.isEmpty()) {
            throw new NotFoundException("Car not found");
        }
        return car.get();
    }

    public Car addCar(Car car) {
        return carRepository.save(car);
    }

    public Car updateCar(Car car) {
        return carRepository.save(car);
    }

    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }

    public List<Car> getCarByFuelType(String fuelType) {
        return carRepository.findByFuelType(fuelType);
    }

    public List<Car> getCarByTransmission(String transmission) {
        return carRepository.findByTransmission(transmission);
    }

    public List<Car> getCarsWithMinimumNumberOfSeats(Integer numberOfSeats) {
        return carRepository.findByNumberOfSeatsGreaterThanEqual(numberOfSeats);
    }

    public List<Car> getCarsWithMaximumPricePerDay(Integer pricePerDay) {
        return carRepository.findByPricePerDayLessThanEqual(pricePerDay);
    }

    public List<Car> getCarsSortedByPricePerDayAsc() {
        return carRepository.findByOrderByPricePerDayAsc();
    }

    public List<Car> getCarsWithMinimumRating(Double rating) {
        return carRepository.findByRatingGreaterThanEqual(rating);
    }

    public List<Car> getCarsByOfficeId(Long officeId) {
        return carRepository.findByOfficeId(officeId);
    }

    public List<Car> getCarsInCity(String city) {
        return carRepository.getCarsInCity(city);
    }

}
