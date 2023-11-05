package com.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.entity.CartEntity;
import com.model.AddPolicyCartRequest;
import com.model.AddPolicyCartResponse;
import com.service.AppService;

@RestController
@RequestMapping("/insurance/cart")
@RefreshScope
public class AppControler {
	@Autowired
	private AppService service;
	@PostMapping("/{userId}/addToCart")
		public ResponseEntity<AddPolicyCartResponse> addPolicy(@PathVariable Long userId,@RequestBody List<AddPolicyCartRequest> addPolicyCartRequest) {
		AddPolicyCartResponse response= service.processAndSend(userId ,addPolicyCartRequest);
			return new ResponseEntity<>(response,HttpStatus.CREATED);
			
		}
@GetMapping("/{userId}")
 public ResponseEntity<CartEntity> getPolicy(@PathVariable("userId") Long userId) {
        return new ResponseEntity<>(service.getAllPolicies(userId),HttpStatus.CREATED);
    }
}
