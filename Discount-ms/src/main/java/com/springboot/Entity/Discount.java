package com.springboot.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Discount {
    @Id
    private Long id;

    @Column(unique = true)
    private Long policyId;
    @Column(unique = true)
    private String code;
    private float discountPercentage;
    private String description;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public float getDiscountPercentage() {
		return discountPercentage;
	}
	public void setDiscountPercentage(float discountPercentage) {
		this.discountPercentage = discountPercentage;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Discount(Long id, String code, float discountPercentage, String description) {
		super();
		this.id = id;
		this.code = code;
		this.discountPercentage = discountPercentage;
		this.description = description;
	}
	public Discount() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
	    
    
}
