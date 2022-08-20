package com.mrityunjoy.prodemics.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mrityunjoy.prodemics.model.Notice;
import com.mrityunjoy.prodemics.repository.NoticeRepository;

@Service
public class NoticeService {
	NoticeRepository noticeRepository;

	@Autowired
	public void setNoticeRepository(NoticeRepository noticeRepository) {
		this.noticeRepository = noticeRepository;
	}

	public List<Notice> getNoticeListSorted() {
		return noticeRepository.findAll(Sort.by("createdAt", "id").descending());
	}
}
