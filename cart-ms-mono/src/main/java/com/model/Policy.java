package com.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder

public class Policy {
	
	private long policy_id;
    private String policy_name;
    private String policy_description;
    private double standardPremium;
    private int coverageAmount; // In months, years, etc.
    private int term;
    private long insurance_id;
    
}
