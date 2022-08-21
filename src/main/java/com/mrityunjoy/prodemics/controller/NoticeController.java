package com.mrityunjoy.prodemics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mrityunjoy.prodemics.annotation.LogAspect;
import com.mrityunjoy.prodemics.dto.NoticeListPaginated;
import com.mrityunjoy.prodemics.service.NoticeService;

import lombok.extern.slf4j.Slf4j;

@RestController()
@Slf4j
@RequestMapping("/notice")
public class NoticeController {

	NoticeService noticeService;
	
	@Autowired
	public void setNoticeService(NoticeService noticeService) {
		this.noticeService = noticeService;
	}
	
	@GetMapping("/list")
	@LogAspect
	public NoticeListPaginated getNoticeList(@RequestParam(required = false, defaultValue = "1") int pageNumber) {
		log.info("Sending response");
		return noticeService.getNoticeListPaginatedAndSorted(pageNumber);
	}
}
