package com.model;

import javax.persistence.Column;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Discount {
    
    private Long id;
    private Long policyId;
    private String code;
    private float discountPercentage;
    private String description;
	
    
    
	    
    
}
