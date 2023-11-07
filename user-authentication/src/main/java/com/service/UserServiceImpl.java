package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dto.ForgotPasswordDTO;
import com.dto.LoginDTO;
import com.dto.SignupDTO;
import com.dto.UserResponseDTO;
import com.entity.UserEntity;
import com.enums.UserRole;
import com.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Override
	@Transactional
	public ResponseEntity<UserResponseDTO> addUser(SignupDTO signupDto) {
		if (isInvalidSignup(signupDto)) {
			return ResponseEntity.badRequest().body(null);
		}

		UserEntity userEntity = signupDto.toEntity();
		userEntity.setUserRole(UserRole.User.name());
		userRepository.save(userEntity);

		return ResponseEntity.status(HttpStatus.CREATED).body(userEntity.toDto());
	}

	private boolean isInvalidSignup(SignupDTO signupDto) {
		return signupDto == null || isNullOrEmpty(signupDto.getUserName()) || !isValidEmail(signupDto.getEmail())
				|| signupDto.getPassword().length() < 6 || !isValidPhoneNumber(signupDto.getPhoneNo());
	}

	private boolean isNullOrEmpty(String str) {
		System.out.println("a");
		return str == null || str.trim().isEmpty();
	}

	private boolean isValidEmail(String email) {
		System.out.println("e");

		return email != null && email.contains("@");
	}

	private boolean isValidPhoneNumber(Long phoneNo) {
		return phoneNo != null && String.valueOf(phoneNo).length() == 10;
	}

	@Override
	public ResponseEntity<UserResponseDTO> login(LoginDTO loginDto) {
		if (loginDto == null || !isValidEmail(loginDto.getEmail()) || isNullOrEmpty(loginDto.getPassword()))
			return ResponseEntity.badRequest().body(null);
		UserEntity userEntity = userRepository.findByEmail(loginDto.getEmail());
		if (userEntity == null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

		if (!userEntity.getPassword().equals(loginDto.getPassword()))
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		return ResponseEntity.ok(userEntity.toDto());
	}

	@Override
	public ResponseEntity<?> forgotPassword(ForgotPasswordDTO forgotPasswordDTO) {

		if (!isValidEmail(forgotPasswordDTO.getEmail()) || isNullOrEmpty(forgotPasswordDTO.getPassword())
				|| isNullOrEmpty(forgotPasswordDTO.getNickname())
				|| isNullOrEmpty(forgotPasswordDTO.getConfirmPassword()))
			return ResponseEntity.badRequest().body(null);
		UserEntity user = userRepository.findByEmail(forgotPasswordDTO.getEmail());
		if (user == null || !user.getNickname().equals(forgotPasswordDTO.getNickname()))
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

		if (!forgotPasswordDTO.getPassword().equals(forgotPasswordDTO.getConfirmPassword()))
			return ResponseEntity.badRequest().body("Passwords do not match");

		user.setPassword(forgotPasswordDTO.getPassword());
		userRepository.save(user);

		return ResponseEntity.ok("Password reset successfully");
	}

}
