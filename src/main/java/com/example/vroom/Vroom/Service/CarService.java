package com.example.vroom.Vroom.Service;

import com.example.vroom.Vroom.Model.Car;
import com.example.vroom.Vroom.Model.Inventory;
import com.example.vroom.Vroom.Model.Office;
import com.example.vroom.Vroom.Repository.BookingRepository;
import com.example.vroom.Vroom.Repository.CarRepository;
import com.example.vroom.Vroom.Repository.OfficeRepository;
import com.example.vroom.Vroom.config.MapperConfig;
import com.example.vroom.Vroom.dto.CarDTO;
import com.example.vroom.Vroom.dto.InventoryDTO;
import com.example.vroom.Vroom.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarService {
    private final CarRepository carRepository;
    private final ModelMapper modelMapper;
    private final BookingRepository bookingRepository;
    private final OfficeRepository officeRepository;

    public List<CarDTO> getAllCars() {
        List<Car> cars = carRepository.findAll();
        return cars.stream().map(car -> modelMapper.map(car, CarDTO.class)).toList();

    }

    public CarDTO getCarById(Long id) throws NotFoundException {
        Optional<Car> car = carRepository.findById(id);
        if (car.isEmpty()) {
            throw new NotFoundException("Car not found");
        }
        return modelMapper.map(car.get(), CarDTO.class);
    }

    public CarDTO addCar(CarDTO car, Long officeId) throws NotFoundException {
        Optional<Office> office = officeRepository.findById(officeId);
        if (office.isEmpty()) {
            throw new NotFoundException("Office not found");
        }
        Car carToAdd = modelMapper.map(car, Car.class);
        return modelMapper.map(carRepository.save(carToAdd), CarDTO.class);
    }

    public CarDTO updateCar(CarDTO car, Long id) throws NotFoundException {
        Optional<Car> carOptional = carRepository.findById(id);
        if (carOptional.isEmpty()) {
            throw new NotFoundException("Car not found");
        }
        Car carToUpdate = carOptional.get();
        carToUpdate.setFuelType(car.getFuelType());
        carToUpdate.setTransmission(car.getTransmission());
        carToUpdate.setNumberOfSeats(car.getNumberOfSeats());
        carToUpdate.setPricePerDay(car.getPricePerDay());
        carToUpdate.setRating(car.getRating());
        return modelMapper.map(carRepository.save(carToUpdate), CarDTO.class);
    }

    public void deleteCar(Long id) throws NotFoundException {
        Optional<Car> car = carRepository.findById(id);
        if (car.isEmpty()) {
            throw new NotFoundException("Car not found");
        }
        bookingRepository.deleteById(id);
    }

    public List<CarDTO> getCarByFuelType(String fuelType) {
        List<Car> cars = carRepository.findByFuelType(fuelType);
        return cars.stream().map(car -> modelMapper.map(car, CarDTO.class)).toList();

    }

    public List<CarDTO> getCarByTransmission(String transmission) {
        List<Car> cars = carRepository.findByTransmission(transmission);
        return cars.stream().map(car -> modelMapper.map(car, CarDTO.class)).toList();
    }

    public List<CarDTO> getCarsWithMinimumNumberOfSeats(Integer numberOfSeats) {
        List<Car> cars = carRepository.findByNumberOfSeatsGreaterThanEqual(numberOfSeats);
        return cars.stream().map(car -> modelMapper.map(car, CarDTO.class)).toList();
    }

    public List<CarDTO> getCarsWithMaximumPricePerDay(Integer pricePerDay) {
        List<Car> cars = carRepository.findByPricePerDayLessThanEqual(pricePerDay);
        return cars.stream().map(car -> modelMapper.map(car, CarDTO.class)).toList();
    }

    public List<CarDTO> getCarsSortedByPricePerDayAsc() {
        List<Car> cars = carRepository.findByOrderByPricePerDayAsc();
        return cars.stream().map(car -> modelMapper.map(car, CarDTO.class)).toList();
    }

    public List<CarDTO> getCarsWithMinimumRating(Double rating) {
        List<Car> cars = carRepository.findByRatingGreaterThanEqual(rating);
        return cars.stream().map(car -> modelMapper.map(car, CarDTO.class)).toList();
    }

    public List<CarDTO> getCarsByOfficeId(Long officeId) {
        List<Car> cars = carRepository.findByOfficeId(officeId);
        return cars.stream().map(car -> modelMapper.map(car, CarDTO.class)).toList();
    }

    public List<CarDTO> getCarsInCity(String city) {
        List<Car> cars = carRepository.getCarsInCity(city);
        return cars.stream().map(car -> modelMapper.map(car, CarDTO.class)).toList();
    }

}
