package com.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.entity.CartEntity;

@Repository
public interface CartRepo extends CrudRepository<CartEntity, Long>{

//	List<CartEntity> findAllById(Integer userId);

}
