package com.cafe24.validator.constraints;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.cafe24.validator.PhoneValidor;

@Retention(RUNTIME)
@Target(FIELD)
@Constraint(validatedBy=PhoneValidor.class)
public @interface PhoneValid {

	String message() default "올바르지 않은 휴대폰 번호 형식 입니다.";
	
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
