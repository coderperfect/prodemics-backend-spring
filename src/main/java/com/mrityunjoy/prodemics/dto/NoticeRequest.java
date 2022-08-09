package com.mrityunjoy.prodemics.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class NoticeRequest {
	String title;
	String description;
	LocalDate createdAt;
}
