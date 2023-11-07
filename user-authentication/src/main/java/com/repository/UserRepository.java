package com.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import com.entity.UserEntity;

@Repository
public interface UserRepository extends CrudRepository<UserEntity,Long> {

	    UserEntity findByUserId(Long userId);
	    UserEntity findByEmail(String userEmail);
}
