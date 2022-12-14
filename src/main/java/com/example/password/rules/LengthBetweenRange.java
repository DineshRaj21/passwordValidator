package com.example.password.rules;

import com.example.password.exceptions.RuleSetupException;
import com.example.password.validator.ValidationResult;

public class LengthBetweenRange extends AbstractValidationRule{

	private final int lower ;
	private final int upper ;
	
	
	public LengthBetweenRange(int lower, int upper){
		if(lower < 0){
			throw new RuleSetupException("lower boundary cannot be less than 0");
		}
		
		if(upper < lower) {
			throw new RuleSetupException("upper cannot be less than lower");
		}
		
		this.lower = lower;
		this.upper = upper;
	}
	
	public int getLower(){
		return this.lower;
	}
	
	public int getUpper(){
		return this.upper;
	}
	
	@Override
	public ValidationResult validatePassword(final String password) {
		
		this.checkExceptions(password);
		
		ValidationResult result = new ValidationResult(false, this.getFailureMessage());
		
		if(password.length() > lower && password.length() < upper){
			result.setSuccess(true);
			result.setValidationMessage("");
		}
		
		return result;
	}

	@Override
	public String getFailureMessage() {
		return "Password must be between " + this.lower + " and " + this.upper + " characters in length";
	}
}
