package com.example.vroom.Vroom.Service;

import com.example.vroom.Vroom.Model.Booking;
import com.example.vroom.Vroom.Model.Car;
import com.example.vroom.Vroom.Model.Client;
import com.example.vroom.Vroom.Model.Feedback;
import com.example.vroom.Vroom.Repository.BookingRepository;
import com.example.vroom.Vroom.Repository.CarRepository;
import com.example.vroom.Vroom.Repository.ClientRepository;
import com.example.vroom.Vroom.Repository.FeedbackRepository;
import com.example.vroom.Vroom.dto.BookingAddRequest;
import com.example.vroom.Vroom.dto.BookingDTO;
import com.example.vroom.Vroom.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final ModelMapper modelMapper;
    private final CarRepository carRepository;
    private final ClientRepository clientRepository;
    private final FeedbackRepository feedbackRepository;

    public List<BookingDTO> getAllBookings() {
        List<Booking> bookings = bookingRepository.findAll();
        return bookings.stream().map(booking -> modelMapper.map(booking, BookingDTO.class)).toList();
    }

    public BookingDTO getBookingById(Long id) throws NotFoundException {
        Optional<Booking> booking = bookingRepository.findById(id);
        if (booking.isEmpty()) {
            throw new NotFoundException("Booking not found");
        }
        return modelMapper.map(booking.get(), BookingDTO.class);
    }

    public BookingDTO addBooking(BookingAddRequest booking) throws NotFoundException {
        Optional<Car> car = carRepository.findById(booking.getCarId());
        if (car.isEmpty()) {
            throw new NotFoundException("Car not found");
        }
        Optional<Client> client = clientRepository.findById(booking.getClientId());
        if (client.isEmpty()) {
            throw new NotFoundException("Client not found");
        }
        Booking bookingToAdd = new Booking();
        bookingToAdd.setStartDate(booking.getStartDate());
        bookingToAdd.setEndDate(booking.getEndDate());
        bookingToAdd.setNumberOfAdditionalDrivers(booking.getNumberOfAdditionalDrivers());
        bookingToAdd.setNumberOfGps(booking.getNumberOfGps());
        bookingToAdd.setNumberOfBabySeats(booking.getNumberOfBabySeats());
        bookingToAdd.setNumberOfExtraBaggage(booking.getNumberOfExtraBaggage());
        bookingToAdd.setCar(car.get());
        bookingToAdd.setClient(client.get());
        bookingToAdd.setFeedback(null);
        long days = bookingToAdd.getEndDate().toEpochDay() - bookingToAdd.getStartDate().toEpochDay();
        bookingToAdd.setPrice(days * car.get().getPricePerDay() +
                (bookingToAdd.getNumberOfAdditionalDrivers() +
                        bookingToAdd.getNumberOfGps() +
                        bookingToAdd.getNumberOfBabySeats()
                        + bookingToAdd.getNumberOfExtraBaggage()) * 10);

        return modelMapper.map(bookingRepository.save(bookingToAdd), BookingDTO.class);
    }


    public BookingDTO updateBooking(BookingDTO booking, Long id) throws NotFoundException {
        Optional<Booking> bookingOptional = bookingRepository.findById(id);
        if (bookingOptional.isEmpty()) {
            throw new NotFoundException("Booking not found");
        }
        Booking bookingToUpdate = bookingOptional.get();
        bookingToUpdate.setStartDate(booking.getStartDate());
        bookingToUpdate.setEndDate(booking.getEndDate());
        bookingToUpdate.setNumberOfAdditionalDrivers(booking.getNumberOfAdditionalDrivers());
        bookingToUpdate.setNumberOfGps(booking.getNumberOfGps());
        bookingToUpdate.setNumberOfBabySeats(booking.getNumberOfBabySeats());
        bookingToUpdate.setNumberOfExtraBaggage(booking.getNumberOfExtraBaggage());
        bookingToUpdate.setPrice(booking.getPrice());
        return modelMapper.map(bookingRepository.save(bookingToUpdate), BookingDTO.class);

    }

    public void deleteBooking(Long id) throws NotFoundException {
        Optional<Booking> booking = bookingRepository.findById(id);
        if (booking.isEmpty()) {
            throw new NotFoundException("Booking not found");
        }

        Optional<Feedback> feedback = feedbackRepository.findById(booking.get().getFeedback().getId());
        feedback.ifPresent(value -> feedbackRepository.deleteById(value.getId()));
        bookingRepository.deleteById(id);
    }

    public List<BookingDTO> getBookingByClientId(Long id) {
        List<Booking> booking = bookingRepository.findByClientId(id);
        return booking.stream().map(b -> modelMapper.map(b, BookingDTO.class)).toList();
    }

    public List<BookingDTO> getBookingByCarId(Long id){
        List<Booking> booking = bookingRepository.findByCarId(id);
        return booking.stream().map(b -> modelMapper.map(b, BookingDTO.class)).toList();
    }

    public BookingDTO getBookingByFeedbackId(Long id) throws NotFoundException {
        Optional<Booking> booking = bookingRepository.findByFeedbackId(id);
        if (booking.isEmpty()) {
            throw new NotFoundException("Booking not found");
        }
        return modelMapper.map(booking.get(), BookingDTO.class);
    }

    public List<BookingDTO> getBookingByStartDate(LocalDate startDate) throws NotFoundException {
        List<Booking> bookings = bookingRepository.findByStartDate(startDate);
        return bookings.stream().map(booking -> modelMapper.map(booking, BookingDTO.class)).toList();
    }

    public List<BookingDTO> getBookingByEndDate(LocalDate endDate) throws NotFoundException {
        List<Booking> bookings = bookingRepository.findByEndDate(endDate);
        return bookings.stream().map(booking -> modelMapper.map(booking, BookingDTO.class)).toList();
    }

    public List<BookingDTO> getBookingByStartDateLessThanEqual(LocalDate startDate) {
        List<Booking> bookings = bookingRepository.findByStartDateLessThanEqual(startDate);
        return bookings.stream().map(booking -> modelMapper.map(booking, BookingDTO.class)).toList();
    }

    public List<BookingDTO> getBookingByStartDateGreaterThanEqual(LocalDate startDate) {
        List<Booking> bookings = bookingRepository.findByStartDateGreaterThanEqual(startDate);
        return bookings.stream().map(booking -> modelMapper.map(booking, BookingDTO.class)).toList();
    }
}
