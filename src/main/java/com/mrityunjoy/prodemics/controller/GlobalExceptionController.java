package com.mrityunjoy.prodemics.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.AllArgsConstructor;
import lombok.Data;

@ControllerAdvice
public class GlobalExceptionController {
	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<ErrorResponse> badCredentialHandler(BadCredentialsException badCredentialsException) {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
				.body(new ErrorResponse(HttpStatus.UNAUTHORIZED.value(), badCredentialsException.getMessage()));
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> methodArgumentNotValidHandler(
			MethodArgumentNotValidException methodArgumentNotValidException) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(),
				getCommaSeparatedValidationErrors(methodArgumentNotValidException)));
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ErrorResponse> messageNotReadableHandler(
			HttpMessageNotReadableException httpMessageNotReadableException) {
		String errorMessage = httpMessageNotReadableException.getMessage();

		String responseMessage = null;

		if (errorMessage.contains("LocalDate")) {
			responseMessage = "Date should be in YYYY-MM-DD format";
		} else if (errorMessage.contains("JSON parse")) {
			responseMessage = "JSON is invalid";
		}

		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), responseMessage));
	}

	@Data
	@AllArgsConstructor
	private class ErrorResponse {
		private int statusCode;
		private String message;
	}

	private String getCommaSeparatedValidationErrors(MethodArgumentNotValidException methodArgumentNotValidException) {
		List<ObjectError> allErrors = methodArgumentNotValidException.getAllErrors();
		return allErrors.stream().map((error) -> error.getDefaultMessage()).collect(Collectors.joining(","));
	}
}
