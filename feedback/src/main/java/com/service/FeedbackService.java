package com.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.dto.FeedbackRequestDTO;
import com.dto.FeedbackResponseDTO;

public interface FeedbackService {

	ResponseEntity<String> addFeedback(FeedbackRequestDTO feedbackDTO, Long uopId);

	ResponseEntity<List<FeedbackResponseDTO>> getAllFeedback();

}
