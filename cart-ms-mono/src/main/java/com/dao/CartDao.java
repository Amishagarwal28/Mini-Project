package com.dao;

import java.util.List;

import com.entity.CartEntity;

public interface CartDao {
	public abstract void addToCart(CartEntity cartEntity);
//	public abstract void deleteFromCart(final Integer userId,final Integer policyId);
	public abstract List<CartEntity> getAllPolicies(Long userId);
	
}
