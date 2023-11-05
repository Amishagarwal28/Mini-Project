package com.model;


import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddPolicyCartResponse {
	private Long cartId;
	private Long userId;
//	private Integer totalItems;
//	private Long discountId;
	private Double totalPremium;
	private List<Policy> policy;
}
