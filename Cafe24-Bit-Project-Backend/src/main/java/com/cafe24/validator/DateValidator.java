package com.cafe24.validator;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.cafe24.validator.constraints.DateValid;

public class DateValidator implements ConstraintValidator<DateValid, String> {

	private Pattern pattern = Pattern.compile("^(19[0-9][0-9]|20\\d{2})-(0[0-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$");
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(value == null || value.length()==0 || "".equals(value))
			return false;
	
		return pattern.matcher(value).matches();
	}
}
