package com.cafe24.validator;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.cafe24.validator.constraints.PasswordValid;

public class PasswordValidator implements ConstraintValidator<PasswordValid, String> {

	private Pattern pattern = Pattern.compile("^.*(?=^.{8,20}$)(?=.*\\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$");
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(value == null || value.length()==0 || "".equals(value))
			return false;
	
		return pattern.matcher(value).matches();
	}

}
