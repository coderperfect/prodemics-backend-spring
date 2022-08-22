package com.mrityunjoy.prodemics.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.mrityunjoy.prodemics.validation.FieldsValueMatchValidator;

@Constraint(validatedBy = FieldsValueMatchValidator.class)
@Retention(RUNTIME)
@Target(TYPE)
public @interface FieldsValueMatch {
	
	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default {};
	
	String message() default "Field values don't match";
	
	String field();
	
	String fieldMatch();
	
	@Retention(RUNTIME)
	@Target(TYPE)
	@interface List {
		FieldsValueMatch[] value();
	}
}
