package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dto.ForgotPasswordDTO;
import com.dto.LoginDTO;
import com.dto.SignupDTO;
import com.dto.UserResponseDTO;
import com.service.UserService;
@RestController
public class UserController {

	@Autowired
    UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<UserResponseDTO> addUser(@RequestBody SignupDTO signupDto)
    {
        return userService.addUser(signupDto);
    }
    @PostMapping("/login")
    public ResponseEntity<UserResponseDTO>login(@RequestBody LoginDTO loginDto) {
        return userService.login(loginDto);
    }
    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestBody ForgotPasswordDTO forgotPasswordDTO){
    	return userService.forgotPassword(forgotPasswordDTO);
    }
   
}
