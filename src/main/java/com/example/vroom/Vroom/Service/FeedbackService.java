package com.example.vroom.Vroom.Service;

import com.example.vroom.Vroom.Model.Booking;
import com.example.vroom.Vroom.Model.Feedback;
import com.example.vroom.Vroom.Repository.BookingRepository;
import com.example.vroom.Vroom.Repository.FeedbackRepository;
import com.example.vroom.Vroom.config.MapperConfig;
import com.example.vroom.Vroom.dto.FeedbackDTO;
import com.example.vroom.Vroom.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FeedbackService {
    private final FeedbackRepository feedbackRepository;
    private final ModelMapper modelMapper;
    private final BookingRepository bookingRepository;
    public List<FeedbackDTO> getAllFeedback() {
        List<Feedback> feedback = feedbackRepository.findAll();
        return feedback.stream().map(feedback1 -> modelMapper.map(feedback1, FeedbackDTO.class)).toList();
    }

    public FeedbackDTO getFeedbackById(Long id) throws NotFoundException {
        Optional<Feedback> feedback = feedbackRepository.findById(id);
        if (feedback.isEmpty()) {
           throw new NotFoundException("Feedback not found");
        }
        return modelMapper.map(feedback.get(), FeedbackDTO.class);
    }
    public FeedbackDTO addFeedback(FeedbackDTO feedback, Long bookingId) throws NotFoundException {
        Optional<Booking> booking = bookingRepository.findById(bookingId);
        if (booking.isEmpty()) {
            throw new NotFoundException("Booking not found");
        }
        Feedback feedbackToAdd = modelMapper.map(feedback, Feedback.class);
        booking.get().setFeedback(feedbackToAdd);
        bookingRepository.save(booking.get());
        return modelMapper.map(feedbackRepository.save(feedbackToAdd), FeedbackDTO.class);
    }
    public void deleteFeedback(Long id) {
        bookingRepository.findByFeedbackId(id).ifPresent(booking -> {
            booking.setFeedback(null);
            bookingRepository.save(booking);
        });
        feedbackRepository.deleteById(id);
    }
}
