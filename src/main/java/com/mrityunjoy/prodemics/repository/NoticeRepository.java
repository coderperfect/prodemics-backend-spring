package com.mrityunjoy.prodemics.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mrityunjoy.prodemics.model.Notice;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Integer> {

}
