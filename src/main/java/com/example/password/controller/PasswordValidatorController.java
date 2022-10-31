package com.example.password.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.password.service.PasswordValidatorService;
import com.example.password.validator.PasswordRequest;
import com.example.password.validator.ValidationResult;

@RestController
@RequestMapping("/api")
public class PasswordValidatorController {
	
	@Autowired
	private PasswordValidatorService pwdValidatorService;
	
	@Autowired
	ValidationResult result;
	
	@GetMapping("/password")
	public ResponseEntity<ValidationResult> validatePassword(@RequestBody PasswordRequest password) {
		
		result = pwdValidatorService.validatePassword(password.getPassword());
		
		return new ResponseEntity<ValidationResult>(result,HttpStatus.OK);
	}

}
