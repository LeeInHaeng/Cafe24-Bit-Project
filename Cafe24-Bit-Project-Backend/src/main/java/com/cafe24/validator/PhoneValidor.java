package com.cafe24.validator;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.cafe24.validator.constraints.PhoneValid;

public class PhoneValidor implements ConstraintValidator<PhoneValid, String> {

	private Pattern pattern = Pattern.compile("^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$");
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(value == null || value.length()==0 || "".equals(value))
			return false;
	
		return pattern.matcher(value).matches();
	}

}