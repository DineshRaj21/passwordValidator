package com.example.password.validator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.example.password.factory.RuleFactory;
import com.example.password.rules.AbstractValidationRule;

@Component
public class PasswordValidator {
	
	
	
	@Autowired
	private ValidationResult result;
	
	@Autowired
	private RuleFactory rulefactory;
	
	@Value("#{'${rules.names}'.split(',')}") 
	private List<String> values;

	/*
	 * public List<AbstractValidationRule> getRules() { return
	 * Collections.unmodifiableList(rules); }
	 */

	public ValidationResult validatePassword(final String password) {
		
		List<AbstractValidationRule> rules = new ArrayList<>();
		AbstractValidationRule abstractRule = null;
		
		for(String rule : this.values) {
			abstractRule = rulefactory.createRule(rule);
			rules.add(abstractRule);
		}
		result = new ValidationResult(false, "");
		
		StringBuilder failureMessages = new StringBuilder();
		
		boolean ruleFailed = false;
		
		for(AbstractValidationRule rule : rules){
			ValidationResult currResult = rule.validatePassword(password);
			
			if(!currResult.isSuccess()){
				failureMessages.append(currResult.getValidationMessage()).append(", ");
				ruleFailed = true;
			}
		}
		
		result.setSuccess(!ruleFailed);
		result.setValidationMessage(failureMessages.toString());
		
		return result;
		
	}

}
