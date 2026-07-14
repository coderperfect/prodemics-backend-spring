package com.mrityunjoy.prodemics.controller;

import com.mrityunjoy.prodemics.dto.NoticeRequest;
import com.mrityunjoy.prodemics.exception.NotFoundException;
import com.mrityunjoy.prodemics.model.Notice;
import com.mrityunjoy.prodemics.repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.mrityunjoy.prodemics.annotation.LogAspect;
import com.mrityunjoy.prodemics.dto.NoticeListPaginated;
import com.mrityunjoy.prodemics.service.NoticeService;

import lombok.extern.slf4j.Slf4j;

import javax.validation.Valid;

@RestController()
@Slf4j
@RequestMapping("/notice")
public class NoticeController {
	NoticeService noticeService;
	NoticeRepository noticeRepository;
	
	@Autowired
	public void setNoticeService(NoticeService noticeService) {
		this.noticeService = noticeService;
	}

	@Autowired
	public void setNoticeRepository(NoticeRepository noticeRepository) {
		this.noticeRepository = noticeRepository;
	}
	
	@GetMapping()
	@LogAspect
	public NoticeListPaginated getNoticeList(@RequestParam(required = false, defaultValue = "1") int pageNumber) {
		log.info("Sending notices for page number - {}", pageNumber);
		return noticeService.getNoticeListPaginatedAndSorted(pageNumber);
	}

	@GetMapping("/{noticeId}")
	@LogAspect
	public Notice getNotice(@PathVariable int noticeId) {
		log.info("Sending notice id - {}", noticeId);
		return noticeService.getNotice(noticeId);
	}

	@PostMapping()
	@LogAspect
	public Notice addNotice(@Valid @RequestBody NoticeRequest noticeRequest) {
		log.info("Adding notice and Sending response");

		return noticeRepository.save(
				new Notice(
						0, noticeRequest.getTitle(), noticeRequest.getDescription(), noticeRequest.getCreatedAt()
				)
		);
	}

	@PutMapping("/{noticeId}")
	@LogAspect
	public Notice updateNotice(@PathVariable int noticeId, @Valid @RequestBody NoticeRequest noticeRequest) {
		log.info("Updating notice with id {} and Sending response", noticeId);

		Notice notice = noticeRepository.findById(noticeId)
				.orElseThrow(() -> new NotFoundException("Notice not found"));

		notice.setTitle(noticeRequest.getTitle());
		notice.setDescription(noticeRequest.getDescription());
		notice.setCreatedAt(noticeRequest.getCreatedAt());

		return noticeRepository.save(notice);
	}

	@DeleteMapping("/{noticeId}")
	@LogAspect
	public void deleteNotice(@PathVariable int noticeId) {
		log.info("Deleting notice with id {}", noticeId);

		Notice notice = noticeRepository.findById(noticeId)
				.orElseThrow(() -> new NotFoundException("Notice not found"));

		noticeRepository.delete(notice);
	}
}
