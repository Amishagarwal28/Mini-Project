package com.springboot.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.Entity.Discount;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Long> {
    public Discount findByCode(String code);
    public Discount findByPolicyId(Long policyId);
}
