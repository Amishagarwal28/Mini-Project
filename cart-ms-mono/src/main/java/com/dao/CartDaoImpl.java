package com.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.entity.CartEntity;
import com.exception.UserNotFoundException;
import com.repo.CartRepo;

public class CartDaoImpl implements CartDao{
	@Autowired
	private CartRepo cartRepo;
	
	@Override
	public void addToCart(final CartEntity cartEntity) {
		cartRepo.save(cartEntity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CartEntity> getAllPolicies(Long userId){
		return (List<CartEntity>) cartRepo.findById(userId)
				.orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));
	}
//	@Override
//	public  void deleteFromCart(final Integer userId,final Integer policyId) {
//		cartRepo.deleteByPolicyId(userId,policyId);
//	}
}
