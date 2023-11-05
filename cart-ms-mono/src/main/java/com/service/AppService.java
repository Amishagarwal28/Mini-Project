package com.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.entity.CartEntity;
import com.exception.UserNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.Policy;
import com.model.AddPolicyCartRequest;
import com.model.AddPolicyCartResponse;
import com.repo.CartRepo;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class AppService {
	@Autowired
	@Qualifier("webclient")
	private WebClient.Builder builder;
	@Autowired
	private CartRepo repo;
	@CircuitBreaker(fallbackMethod="fbm",name="cb")
	public AddPolicyCartResponse  processAndSend(Long userId, List<AddPolicyCartRequest> addPolicyCartRequests) {
		ObjectMapper mapper= new ObjectMapper();
		String policyServiceURL="http://localhost:8882/policies/"+ addPolicyCartRequests.stream()
		.map(e ->String.valueOf(e.getPolicyId())).collect(Collectors.joining(","));
		
		List<Policy> policyServiceList= builder.build()
				.get()
				.uri(policyServiceURL)
				.retrieve()
				.bodyToFlux(Policy.class)
				.collectList()
				.block();
		System.out.println(policyServiceURL);
		System.out.println(policyServiceList);
		
		final Double[] totalpremium= {0.0};
		policyServiceList.forEach(ps1->{addPolicyCartRequests.forEach(cr->{
				if(ps1.getPolicy_id()==cr.getPolicyId()) {
					totalpremium[0]=totalpremium[0]+ps1.getStandardPremium();
				}
			});
		});
		
		
		
		CartEntity cartEntity=null;
		try {
			cartEntity=CartEntity.builder()
					.userId(userId)
					.totalItems(policyServiceList.size())
					//.discountId()
					.totalPremium(totalpremium[0])
					.policy(mapper.writeValueAsString(policyServiceList))
					.build();
			cartEntity=repo.save(cartEntity);
		}catch(Exception e) {
			
		}
		//return response
		AddPolicyCartResponse res=AddPolicyCartResponse.builder()
				.userId(cartEntity.getUserId())
				.cartId(cartEntity.getCartId())
				//.totalItems(cartEntity.getTotalItems())
				//.discountId(cartEntity.getDiscountId())
				.totalPremium(cartEntity.getTotalPremium())
				.policy(policyServiceList)
				.build();
		return res;
				
	}
	public  AddPolicyCartResponse fbm(Throwable t) {
		System.out.println("server down");
		return null; 
	}
	@SuppressWarnings("unchecked")
	public CartEntity getAllPolicies(Long userId){
		return (CartEntity) repo.findById(userId)
				.orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));
	}

}