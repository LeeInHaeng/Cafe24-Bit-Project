package com.cafe24.validator;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.cafe24.validator.constraints.NameValid;

public class NameValidator implements ConstraintValidator<NameValid, String> {

	private Pattern pattern = Pattern.compile("^[가-힣]{2,4}|[a-zA-Z]{2,10}\\s[a-zA-Z]{2,10}$");
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(value == null || value.length()==0 || "".equals(value))
			return false;
	
		return pattern.matcher(value).matches();
	}
}
