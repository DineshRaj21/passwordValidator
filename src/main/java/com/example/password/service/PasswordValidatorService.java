package com.example.password.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.password.validator.PasswordValidator;
import com.example.password.validator.ValidationResult;

@Service
public class PasswordValidatorService {
	
	@Autowired
	private PasswordValidator passwordValidator;
	
	public ValidationResult validatePassword( final String password) {
		return passwordValidator.validatePassword(password); 
	}

}
