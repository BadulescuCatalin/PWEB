package com.example.vroom.Vroom.Repository;

import com.example.vroom.Vroom.Model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByClientId(Long id);

    List<Booking> findByCarId(Long id);

    Optional<Booking> findByFeedbackId(Long id);

    List<Booking> findByStartDate(LocalDate startDate);

    List<Booking> findByEndDate(LocalDate endDate);
    List<Booking> findByStartDateLessThanEqual(LocalDate startDate);
    List<Booking> findByStartDateGreaterThanEqual(LocalDate startDate);
    List<Booking> findByEndDateLessThanEqual(LocalDate endDate);
    List<Booking> findByEndDateGreaterThanEqual(LocalDate endDate);

}
