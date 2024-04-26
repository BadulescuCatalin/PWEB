package com.example.vroom.Vroom.Controller;

import com.example.vroom.Vroom.Model.Feedback;
import com.example.vroom.Vroom.Service.FeedbackService;
import com.example.vroom.Vroom.dto.FeedbackDTO;
import com.example.vroom.Vroom.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/feedback")
public class FeedbackController {
    private final FeedbackService feedbackService;

    @GetMapping("/allFeedback")
    public ResponseEntity<List<FeedbackDTO>> getAllFeedback() {
        List<FeedbackDTO> feedback = feedbackService.getAllFeedback();
        if (feedback != null) {
            return ResponseEntity
                    .ok()
                    .body(feedback);
        }
        return ResponseEntity
                .badRequest()
                .body(null);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FeedbackDTO> getFeedbackById(@PathVariable Long id) throws NotFoundException {
        FeedbackDTO feedbackDTO = feedbackService.getFeedbackById(id);
        if (feedbackDTO != null) {
            return ResponseEntity
                    .ok()
                    .body(feedbackDTO);
        }
        return ResponseEntity
                .badRequest()
                .body(null);
    }

    @PostMapping("/addFeedback")
    public ResponseEntity<FeedbackDTO> addFeedback(@RequestBody FeedbackDTO feedbackDTO, @RequestParam Long bookingId) throws NotFoundException {
        FeedbackDTO feedback = feedbackService.addFeedback(feedbackDTO, bookingId);
        if (feedback != null) {
            return ResponseEntity
                    .ok()
                    .body(feedback);
        }
        return ResponseEntity
                .badRequest()
                .body(null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFeedback(@PathVariable Long id) {
        feedbackService.deleteFeedback(id);
        return ResponseEntity
                .ok()
                .build();
    }
}
