package com.dto;

import java.util.Date;

import com.entity.FeedbackEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FeedbackResponseDTO {
	private Long uopId;
	private String comment;
	private Date feedbackDate;

	
}
