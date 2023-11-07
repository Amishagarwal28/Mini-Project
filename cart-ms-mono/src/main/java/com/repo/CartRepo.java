package com.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.entity.CartEntity;
import com.google.common.base.Optional;

@Repository
public interface CartRepo extends CrudRepository<CartEntity, Long>{

	public CartEntity findByuserId(Long userId);
	

}
