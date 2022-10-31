package com.example.password.factory;

import org.springframework.stereotype.Component;

import com.example.password.rules.AbstractValidationRule;
import com.example.password.rules.AtleastOneNumberAndLowerCaseLetter;
import com.example.password.rules.LengthBetweenRange;
import com.example.password.rules.LowerCaseAndNumbersOnly;
import com.example.password.rules.NoRepeatingCharacterSequences;

@Component
public class RuleFactory {
	
	public AbstractValidationRule createRule(String rule) {
		
		if(rule == null || rule.isEmpty()) {
			return null;
		}
		switch(rule) {
		case "AtleastOne" :
			return new AtleastOneNumberAndLowerCaseLetter();
		case "Length" :
			return new LengthBetweenRange(5,12);
		case "LowerCase" :
			return new LowerCaseAndNumbersOnly();
		case "NoRepeat" :
			return new NoRepeatingCharacterSequences();
		default :
			throw new IllegalArgumentException("I");
		}
			
	}

}
