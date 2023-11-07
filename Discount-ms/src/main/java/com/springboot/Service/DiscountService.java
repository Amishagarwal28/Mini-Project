package com.springboot.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.Entity.Discount;
import com.springboot.Repository.DiscountRepository;

@Service
public class DiscountService {
    @Autowired
    private DiscountRepository discountRepository;

    public List<Discount> getAllDiscounts() {
        return discountRepository.findAll();
    }

    public Discount getDiscountById(Long id) {
        return discountRepository.findById(id).orElse(null);
    }
    public Discount getDiscountByPolicyId(Long policyId) {
        Discount dis=discountRepository.findByPolicyId(policyId);
        if(dis==null) {
        	return dis;
        }
    	return dis;
    }

    public Discount createDiscount(Discount discount) {
        return discountRepository.save(discount);
    }

    public Discount updateDiscount(Long id, Discount updatedDiscount) {
        if (discountRepository.existsById(id)) {
            updatedDiscount.setId(id);
            return discountRepository.save(updatedDiscount);
        }
        return null;
    }

    public void deleteDiscount(Long id) {
        discountRepository.deleteById(id);
    }

    public double applyDiscount(String code, double cartTotal) {
        Discount discount = discountRepository.findByCode(code);
        if (discount != null) {
            cartTotal = cartTotal - (cartTotal*discount.getDiscountPercentage()/100);
        }
        return cartTotal;
    }

}
