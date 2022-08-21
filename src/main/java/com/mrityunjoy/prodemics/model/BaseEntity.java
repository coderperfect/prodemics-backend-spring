package com.mrityunjoy.prodemics.model;

import javax.persistence.EntityListeners;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {

	@ManyToOne(targetEntity = EndUser.class)
	@JoinColumn(name = "created_by", referencedColumnName = "username", updatable = false)
	@CreatedBy
	private EndUser createdBy;

}
