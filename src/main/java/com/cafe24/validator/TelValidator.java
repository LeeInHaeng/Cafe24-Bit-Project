package com.cafe24.validator;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.cafe24.validator.constraints.TelValid;

public class TelValidator implements ConstraintValidator<TelValid, String> {

	private Pattern pattern = Pattern.compile("^\\d{2,3}-\\d{3,4}-\\d{4}$");
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(value == null || value.length()==0 || "".equals(value))
			return false;
	
		return pattern.matcher(value).matches();
	}

}
