package com.mrityunjoy.prodemics.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mrityunjoy.prodemics.annotation.LogAspect;
import com.mrityunjoy.prodemics.model.Notice;
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
	public List<Notice> getNoticeList() {
		log.info("Sending response");
		return noticeService.getNoticeListSorted();
	}
}
