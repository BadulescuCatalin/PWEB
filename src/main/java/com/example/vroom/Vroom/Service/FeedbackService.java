package com.example.vroom.Vroom.Service;

import com.example.vroom.Vroom.Model.Feedback;
import com.example.vroom.Vroom.Repository.FeedbackRepository;
import com.example.vroom.Vroom.config.MapperConfig;
import com.example.vroom.Vroom.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FeedbackService {
    private final FeedbackRepository feedbackRepository;
    private final MapperConfig mapperConfig;
    public List<Feedback> getAllFeedback() {
        return feedbackRepository.findAll();
    }

    public Feedback getFeedbackById(Long id) throws NotFoundException {
        Optional<Feedback> feedback = feedbackRepository.findById(id);
        if (feedback.isEmpty()) {
           throw new NotFoundException("Feedback not found");
        }
        return feedback.get();
    }
    public Feedback addFeedback(Feedback feedback) {
        return feedbackRepository.save(feedback);
    }
    public void deleteFeedback(Long id) {
        feedbackRepository.deleteById(id);
    }

}
