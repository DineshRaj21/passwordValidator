package com.example.password.validator;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.password.rules.AbstractValidationRule;
import com.example.password.rules.AtleastOneNumberAndLowerCaseLetter;
import com.example.password.rules.LengthBetweenRange;
import com.example.password.rules.LowerCaseAndNumbersOnly;
import com.example.password.rules.NoRepeatingCharacterSequences;

@Component
public class PasswordValidator {
	
	
	private List<AbstractValidationRule> rules;
@Autowired
private ValidationResult result;

	
	public PasswordValidator(final List<AbstractValidationRule> rules) {
		
		AtleastOneNumberAndLowerCaseLetter atleast = new AtleastOneNumberAndLowerCaseLetter();
		LengthBetweenRange lengthRange = new LengthBetweenRange(5,12);
		LowerCaseAndNumbersOnly lowerCase = new LowerCaseAndNumbersOnly();
		NoRepeatingCharacterSequences noRepeat = new NoRepeatingCharacterSequences();
		rules.add(lengthRange);
		rules.add(atleast);
		rules.add(lowerCase);
		rules.add(noRepeat);
		
		this.rules = rules;
	}
	
	public List<AbstractValidationRule> getRules() {
		return Collections.unmodifiableList(rules);
	}

	public ValidationResult validatePassword(final String password) {
		
		result = new ValidationResult(false, "");
		
		StringBuilder failureMessages = new StringBuilder();
		
		boolean ruleFailed = false;
		
		for(AbstractValidationRule rule : this.getRules()){
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
