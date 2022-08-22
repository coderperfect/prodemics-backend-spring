package com.mrityunjoy.prodemics.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.mrityunjoy.prodemics.annotation.FieldsValueMatch;

import lombok.Data;

@Data
@FieldsValueMatch.List({
		@FieldsValueMatch(field = "emailId", fieldMatch = "confirmEmailId", message = "Email Ids don't match"),
		@FieldsValueMatch(field = "password", fieldMatch = "confirmPassword", message = "Passwords don't match")
})
public class SignUpRequest {

	@NotBlank(message = "username shouldn't be blank")
	private String username;
	
	@NotBlank(message = "Email shouldn't be blank")
	@Email(message = "Email is invalid")
	private String emailId;
	
	private String confirmEmailId;
	
	@NotBlank(message = "Name shouldn't be blank")
	private String name;
	
	@NotBlank(message = "Password shouldn't be blank")
	private String password;
	
	private String confirmPassword;
}
