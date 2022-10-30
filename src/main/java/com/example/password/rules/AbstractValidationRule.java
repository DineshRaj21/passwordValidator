package com.example.password.rules;

import com.example.password.exceptions.ValidationException;
import com.example.password.validator.ValidationResult;

public abstract class AbstractValidationRule {
	
public abstract ValidationResult validatePassword(final String password);
	
	public abstract String getFailureMessage();
	
	protected void checkExceptions(final String password){
		if(password == null){
			throw new ValidationException("Password string cannot be null");
		}
	}

}
