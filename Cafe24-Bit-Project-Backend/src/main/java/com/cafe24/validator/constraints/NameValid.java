package com.cafe24.validator.constraints;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.cafe24.validator.NameValidator;

@Retention(RUNTIME)
@Target(FIELD)
@Constraint(validatedBy=NameValidator.class)
public @interface NameValid {

	String message() default "한글이름 2~4글자, 영문이름 2~10글자 사이로 입력해 주세요.";
	
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
