package com.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.dto.FeedbackResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "feedback")
public class FeedbackEntity {

	@Id
	@GeneratedValue
	private Long feedbackId;
	private Long uopId;
	private String comment;
	private Date feedbackDate;
	public FeedbackResponseDTO toDto() {
		return FeedbackResponseDTO.builder().comment(comment).feedbackDate(feedbackDate).uopId(uopId).build();
	}

}
