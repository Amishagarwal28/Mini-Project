package com.dao;

import java.util.List;

import com.model.UserHealthData;
import com.model.UserOwnedPolicy;
import com.model.Policy;


public interface UOPDAO {
	public List<Policy> showAllPolicy();
	public String buyPolicy(UserHealthData uhd, Long userId);
	public List<UserOwnedPolicy> loadUserOwnedPolicies(Long userId);
	
}
