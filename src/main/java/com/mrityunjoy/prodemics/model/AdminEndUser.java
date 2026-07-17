package com.mrityunjoy.prodemics.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "admin_end_user", schema = "prodemics")
public class AdminEndUser extends EndUser {

	@OneToMany(mappedBy = "createdBy")
	List<Notice> notices;

}
