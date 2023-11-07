package com.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Policy {

    private long policy_id;
    private String policy_name;
    private String policy_description;
    private double standardPremium;
    private int coveragePeriod; // In months, years, etc.
    private int term;
    private long insurance_id;
}