package com.example.vroom.Vroom.Service;

import com.example.vroom.Vroom.Model.Booking;
import com.example.vroom.Vroom.Repository.BookingRepository;
import com.example.vroom.Vroom.config.MapperConfig;
import com.example.vroom.Vroom.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final MapperConfig mapperConfig;
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Booking getBookingById(Long id) throws NotFoundException {
        Optional<Booking> booking = bookingRepository.findById(id);
        if (booking.isEmpty()) {
            throw new NotFoundException("Booking not found");
        }
        return booking.get();
    }

    public Booking addBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    public Booking updateBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    public void deleteBooking(Long id) {
        bookingRepository.deleteById(id);
    }

    public Booking getBookingByClientId(Long id) throws NotFoundException {
        Optional<Booking> booking = bookingRepository.findByClientId(id);
        if (booking.isEmpty()) {
            throw new NotFoundException("Booking not found");
        }
        return booking.get();
    }

    public Booking getBookingByCarId(Long id) throws NotFoundException {
        Optional<Booking> booking = bookingRepository.findByCarId(id);
        if (booking.isEmpty()) {
            throw new NotFoundException("Booking not found");
        }
        return booking.get();
    }

    public Booking getBookingByFeedbackId(Long id) throws NotFoundException {
        Optional<Booking> booking = bookingRepository.findByFeedbackId(id);
        if (booking.isEmpty()) {
            throw new NotFoundException("Booking not found");
        }
        return booking.get();
    }

    public List<Booking> getBookingByStartDate(LocalDate startDate) throws NotFoundException {
        return bookingRepository.findByStartDate(startDate);
    }

    public List<Booking> getBookingByEndDate(LocalDate endDate) throws NotFoundException {
        return bookingRepository.findByEndDate(endDate);
    }

    public List<Booking> getBookingByStartDateLessThanEqual(LocalDate startDate) {
        return bookingRepository.findByStartDateLessThanEqual(startDate);
    }

    public List<Booking> getBookingByStartDateGreaterThanEqual(LocalDate startDate) {
        return bookingRepository.findByStartDateGreaterThanEqual(startDate);
    }


}
