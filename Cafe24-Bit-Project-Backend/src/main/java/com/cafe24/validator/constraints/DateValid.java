package com.cafe24.validator.constraints;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.cafe24.validator.DateValidator;

@Retention(RUNTIME)
@Target(FIELD)
@Constraint(validatedBy=DateValidator.class)
public @interface DateValid {

	String message() default "YYYY-MM-DD 의 형식이 아닙니다.";
	
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
