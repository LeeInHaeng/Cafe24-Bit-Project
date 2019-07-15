package com.cafe24.validator.constraints;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.cafe24.validator.PasswordValidator;

@Retention(RUNTIME)
@Target(FIELD)
@Constraint(validatedBy=PasswordValidator.class)
public @interface PasswordValid {

	String message() default "비밀번호는 8~20자 숫자, 영문, 특수문자가 포함되어야 합니다.";
	
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
