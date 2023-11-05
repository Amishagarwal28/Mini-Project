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
import com.dao.UOPDAO;
import com.model.Policy;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/user-dashboard")
@RefreshScope
public class RestController {
	@Autowired
	private UOPDAO dao;
	
	

	@SuppressWarnings("rawtypes")
	@GetMapping("/showAllpolicy")
	public ResponseEntity  showAllPolicy(){
		List<Policy> policies=dao.showAllPolicy();
	return new ResponseEntity<>(policies, HttpStatus.CREATED);
	}
	
	@SuppressWarnings("rawtypes")
	@PostMapping("/buyPolicy/{userId}")
	public ResponseEntity buyPolicy(@RequestBody com.model.UserHealthData uhd,@PathVariable Long userId ) {
		return new ResponseEntity<>(dao.buyPolicy(uhd, userId), HttpStatus.CREATED);
	}
	@SuppressWarnings("rawtypes")
	@GetMapping("/loadUserPolicies/{userId}")
	public ResponseEntity loadPolicies(@PathVariable Long userId) {
		return new ResponseEntity<>(dao.loadUserOwnedPolicies(userId), HttpStatus.CREATED);
	}
	}
