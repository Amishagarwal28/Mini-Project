package com.entity;

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
public class CartEntity {
	
	@Id
	@GeneratedValue
	private Long cartId;
	private Long userId;
	private Integer totalItems;
	//private Long discountId;
	private Double totalPremium;
	private String policy;
}
