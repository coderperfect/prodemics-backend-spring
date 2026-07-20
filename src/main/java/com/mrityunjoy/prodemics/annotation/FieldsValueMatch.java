package com.mrityunjoy.prodemics.annotation;

import java.lang.annotation.*;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import com.mrityunjoy.prodemics.validation.FieldsValueMatchValidator;

@Constraint(validatedBy=FieldsValueMatchValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Repeatable(FieldsValueMatch.List.class)
public @interface FieldsValueMatch {
	
	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default {};
	
	String message() default "Field values don't match";
	
	String field();
	
	String fieldMatch();
	
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.TYPE)
	@interface List {
		FieldsValueMatch[] value();
	}
}
