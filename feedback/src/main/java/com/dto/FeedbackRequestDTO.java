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
public class FeedbackRequestDTO {
	private String comment;
	private Date feedbackDate;

	public FeedbackEntity toEntity() {
		return FeedbackEntity.builder().comment(comment).feedbackDate(feedbackDate).build();
	}
}
