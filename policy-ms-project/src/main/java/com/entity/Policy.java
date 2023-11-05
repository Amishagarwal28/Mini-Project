package com.entity;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Policy {

	@Id
	@GeneratedValue
    private long policy_id;
    private String policy_name;
    private String policy_description;
    private double standardPremium;
    private int coverageAmount; // In months, years, etc.
    private int term;
    private long insurance_id;
}