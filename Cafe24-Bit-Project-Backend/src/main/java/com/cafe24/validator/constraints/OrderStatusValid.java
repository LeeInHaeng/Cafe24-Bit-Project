package com.cafe24.validator.constraints;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.cafe24.validator.OrderStatusValidator;

@Retention(RUNTIME)
@Target(FIELD)
@Constraint(validatedBy=OrderStatusValidator.class)
public @interface OrderStatusValid {

	String message() default "올바르지 않은 요청 입니다.";
	
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
