package com.mrityunjoy.prodemics.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mrityunjoy.prodemics.dto.NoticeListPaginated;
import com.mrityunjoy.prodemics.model.Notice;
import com.mrityunjoy.prodemics.repository.NoticeRepository;

@Service
public class NoticeService {
	NoticeRepository noticeRepository;

	@Autowired
	public void setNoticeRepository(NoticeRepository noticeRepository) {
		this.noticeRepository = noticeRepository;
	}

	public NoticeListPaginated getNoticeListPaginatedAndSorted(int pageNumber) {
		Pageable pageable = PageRequest.of(pageNumber - 1, 5, Sort.by("createdAt", "id").descending());
		Page<Notice> noticePage = noticeRepository.findAll(pageable);
		return new NoticeListPaginated(noticePage.getContent(), pageNumber, noticePage.getTotalPages());
	}
}
