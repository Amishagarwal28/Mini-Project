package com.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.model.UserOwnedPolicy;

@Repository
public interface UOPRepo extends JpaRepository<UserOwnedPolicy, Long> {
	public List<UserOwnedPolicy> findAllByuserId(Long userId);
}
