package com.dao;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.model.UserHealthData;
import com.model.UserOwnedPolicy;
import com.repo.UOPRepo;
import com.model.Policy;
import com.service.AppService;

@Service
public class UOPDAOImpl implements UOPDAO{
	
	@Autowired
	private AppService service;
	

	@Override
	//@Transactional
	public String buyPolicy(UserHealthData uhd, Long userId) {
		Double rate =1.0;
		if(uhd.isDrinker()) {
			rate+=0.1;
		}
		if(uhd.isSmoker()) {
			rate+=0.2;
		}
		if(uhd.isHasTerminalDisease()) {
			rate+=0.5;
		}
		
	
		return service.buyPolicies(userId, rate);
	}

	@Override
	//@Transactional
	public List<UserOwnedPolicy> loadUserOwnedPolicies(Long userId) {
				
		return service.loadUserOwnedPolicies(userId);
	}

	

	@Override
//	@Transactional
	public List<Policy> showAllPolicy() {
		
		return service.showAllPolicy();
	}
	

}
