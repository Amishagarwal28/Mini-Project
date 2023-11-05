package com.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.exception.PolicyNotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.model.CartEntity;
import com.model.Policy;
import com.model.UserOwnedPolicy;
import com.repo.UOPRepo;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class AppService {
	@Autowired
	@Qualifier("webclient")
	private WebClient.Builder builder;
	
	@Autowired
	private UOPRepo repo;
	
	@CircuitBreaker(fallbackMethod = "fallbackMethod", name="circuit")
	public String  buyPolicies(Long userId,Double rate) {
		ObjectMapper mapper= new ObjectMapper();
		String policyServiceURL="http://cart-micro-service/insurance/cart"+userId;
		CartEntity crt = builder.build()
				.get()
				.uri(policyServiceURL)
				.retrieve()
				.bodyToFlux(CartEntity.class)
				.collectList()
				.block().get(0);
		try {
			List<Policy> policies = (List<Policy>) mapper.readValue(crt.getPolicy(), new TypeReference<List<Policy>>(){});
			for(Policy policy:policies) {
				UserOwnedPolicy uop = null;
				uop = UserOwnedPolicy.builder().userId(userId).policyId(policy.getPolicy_id()).startDate(Date.valueOf(LocalDate.now())).status("Pending").userPremium(policy.getStandardPremium()*rate).userCoverage(policy.getCoverageAmount()).userTerm(policy.getTerm()).build();
			repo.save(uop);
			}
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} 
		
		return "";
		
					
	}
	
	
	
	@CircuitBreaker(fallbackMethod = "fallbackMethod", name="circuit")
	public List<Policy>  showAllPolicy() {
		
		String policyServiceURL="http://policy-micro-service/policy/loadPolicies";
		
		return builder.build()
				.get()
				.uri(policyServiceURL)
				.retrieve()
				.bodyToFlux(Policy.class)
				.collectList()
				.block();
					
	}
	public List<UserOwnedPolicy>  loadUserOwnedPolicies(Long userId) {
				
		return repo.findAll();
					
	}
	public void deletePolicy(long id) {
		// TODO Auto-generated method stub
		repo.findById(id).orElseThrow(
				() -> new PolicyNotFoundException(id));

		repo.deleteById(id);
		
		
	}
	public String fallbackMethod() {
		return "Service down..";
	}
}
