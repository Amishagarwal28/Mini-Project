package com.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.exception.PolicyNotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.CartEntity;
import com.model.Discount;
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

	private HashMap<Long, Discount> discountMap = new HashMap<>();

	@CircuitBreaker(fallbackMethod = "fbm", name = "circuit")
	public String getDiscount() {

		String discountServiceURL = "http://discount-service/loadAll";
		List<Discount> discounts = builder.build().get().uri(discountServiceURL).retrieve().bodyToFlux(Discount.class)
				.collectList().block();
		for (Discount dis : discounts) {
			discountMap.put(dis.getPolicyId(), dis);
		}
		return "added";

	}

	@CircuitBreaker(fallbackMethod = "fbm", name = "circuit")
	public String buyPolicies(Long userId, Double rate) {
		ObjectMapper mapper = new ObjectMapper();
		String policyServiceURL = "http://cart-service/insurance/cart/" + userId;
		CartEntity crt = builder.build().get().uri(policyServiceURL).retrieve().bodyToFlux(CartEntity.class).last()
				.block();
		System.out.println(crt);
		try {
			List<Policy> policies = (List<Policy>) mapper.readValue(crt.getPolicy(), new TypeReference<List<Policy>>() {
			});

			for (Policy policy : policies) {
				UserOwnedPolicy uop = null;
				Double dis;
				if (discountMap.get(policy.getPolicy_id()) == null) {
					dis = 0.0;
				} else {
					dis = (double) discountMap.get(policy.getPolicy_id()).getDiscountPercentage();
				}
				uop = UserOwnedPolicy.builder().userId(userId).policyId(policy.getPolicy_id())
						.startDate(Date.valueOf(LocalDate.now())).status("Approved")
						.userPremium(policy.getStandardPremium() * rate * (1.0 - dis))
						.userCoverage(policy.getCoveragePeriod()).userTerm(policy.getTerm()).build();
				repo.save(uop);
			}
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return "added";

	}

	@CircuitBreaker(fallbackMethod = "fallbackMethod", name = "circuit2")
	public List<Policy> showAllPolicy() {
		System.out.println("reached");
		String policyServiceURL = "http://policy-service/policies/loadAllPolicies";
		System.out.println("reached down");

		return builder.build().get().uri(policyServiceURL).retrieve().bodyToFlux(Policy.class).collectList().block();

	}

	public List<UserOwnedPolicy> loadUserOwnedPolicies(Long userId) {

		return repo.findAllByuserId(userId);

	}

	public void deletePolicy(long id) {
		repo.findById(id).orElseThrow(() -> new PolicyNotFoundException(id));

		repo.deleteById(id);

	}

	public String fbm(Throwable t) {
		System.out.println("server down");
		return null;
	}

	public List<Policy> fallbackMethod(Throwable t) {
		System.out.println("server down");
		return null;
	}
}
