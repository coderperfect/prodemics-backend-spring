package com.mrityunjoy.prodemics.dto;

import java.util.List;

import com.mrityunjoy.prodemics.model.Notice;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NoticeListPaginated {
	private List<Notice> notices;
	private int currentPage;
	private int totalPages;
}
