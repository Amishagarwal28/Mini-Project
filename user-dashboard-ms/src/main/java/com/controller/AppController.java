package com.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dao.UOPDAO;
import com.model.Policy;


@RestController
@RequestMapping("/user-dashboard")
public class AppController {
	@Autowired
	private UOPDAO dao;
	
	
	@GetMapping("/showAlPolicy")
	public ResponseEntity<String>  showllPolicy(){
		System.out.println("reached");
		
		return new ResponseEntity<String>("created", HttpStatus.CREATED);
	}
	@PutMapping("/{uopId}/delete")
	public String updateUOP(@PathVariable Long uopId) {
		return "updated";
	}
		
	@DeleteMapping("/{uopId}")
	public String deleteUOP(@PathVariable Long uopId) {
		
		return "deleted";
	}
	@PostMapping("/buyPolicy/{userId}")
	public ResponseEntity<String> buyPolicy(@RequestBody com.model.UserHealthData uhd,@PathVariable Long userId ) {
		return new ResponseEntity<>(dao.buyPolicy(uhd, userId), HttpStatus.CREATED);
	}
	@SuppressWarnings("rawtypes")
	@GetMapping("/loadUserPolicies/{userId}")
	public ResponseEntity loadPolicies(@PathVariable Long userId) {
		return new ResponseEntity<>(dao.loadUserOwnedPolicies(userId), HttpStatus.CREATED);
	}
	}
