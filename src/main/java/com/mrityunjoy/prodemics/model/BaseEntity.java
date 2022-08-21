package com.mrityunjoy.prodemics.model;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;

import lombok.Data;

@Data
@MappedSuperclass
public class BaseEntity {

	@ManyToOne(targetEntity = EndUser.class, optional = false)
	@JoinColumn(name = "created_by", referencedColumnName = "username")
	@CreatedBy
	private EndUser createdBy;

}
