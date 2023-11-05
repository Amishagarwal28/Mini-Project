package com.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.model.UserOwnedPolicy;

public interface UOPRepo extends JpaRepository<UserOwnedPolicy, Long> {

}
