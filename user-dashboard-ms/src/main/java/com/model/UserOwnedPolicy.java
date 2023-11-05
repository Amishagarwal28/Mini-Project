package com.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
public class UserOwnedPolicy {
	@Id
	@GeneratedValue
	private Long uopId;
	private Long userId;
	private Long policyId;
	private Double userPremium;
	private int userCoverage;
	private int userTerm;
	private String status;
	private Date startDate;
	
}
