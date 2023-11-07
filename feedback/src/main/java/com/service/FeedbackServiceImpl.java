package com.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dto.FeedbackRequestDTO;
import com.dto.FeedbackResponseDTO;
import com.entity.FeedbackEntity;
import com.repository.FeedbackRepository;

@Service
public class FeedbackServiceImpl implements FeedbackService {

	@Autowired
	FeedbackRepository feedbackRepository;

	@Override
	@Transactional
	public ResponseEntity<String> addFeedback(FeedbackRequestDTO feedbackDTO, Long uopId) {

		FeedbackEntity feedbackEntity = feedbackDTO.toEntity();
		feedbackEntity.setUopId(uopId);
		feedbackRepository.save(feedbackEntity);
		return ResponseEntity.status(HttpStatus.CREATED).body("Feedback has been creadted successfully");
	}

	@Override
	public ResponseEntity<List<FeedbackResponseDTO>> getAllFeedback() {
		List<FeedbackEntity> feedbackList = (List<FeedbackEntity>) feedbackRepository.findAll();
		List<FeedbackResponseDTO> feedbackResponseDTOs = new ArrayList<>();
		for (FeedbackEntity feedback : feedbackList) {
			feedbackResponseDTOs.add(feedback.toDto());
		}
		return ResponseEntity.ok(feedbackResponseDTOs);
	}

}
