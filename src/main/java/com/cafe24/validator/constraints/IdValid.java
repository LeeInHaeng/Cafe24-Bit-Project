package com.cafe24.validator.constraints;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.cafe24.validator.IdValidator;

@Retention(RUNTIME)
@Target(FIELD)
@Constraint(validatedBy=IdValidator.class)
public @interface IdValid {

	String message() default "ID를 영문과 숫자로 1~255자 사이로 입력해 주세요.";
	
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
