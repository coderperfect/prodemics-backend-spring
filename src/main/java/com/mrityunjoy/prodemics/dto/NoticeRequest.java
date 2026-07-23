package com.mrityunjoy.prodemics.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class NoticeRequest {

	@NotBlank(message = "title can't be blank")
	@Length(max = 200, message = "title is limited to 200 characters long")
	String title;

	@NotNull(message = "noticeDate can't be null")
	LocalDate noticeDate;

	@NotBlank(message = "description can't be blank")
	@Length(max = 1000, message = "description is limited to 1000 characters long")
	String description;
}
