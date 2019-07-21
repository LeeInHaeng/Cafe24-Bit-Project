package com.cafe24.validator;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.cafe24.validator.constraints.OrderStatusValid;

public class OrderStatusValidator implements ConstraintValidator<OrderStatusValid, String> {

	private Pattern pattern = Pattern.compile("취소|교환|반품|환불");
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(value == null || value.length()==0 || "".equals(value))
			return false;
	
		return pattern.matcher(value).matches();
	}
}
