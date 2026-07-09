package com.mrityunjoy.prodemics.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "admin_end_user", schema = "prodemics")
public class AdminEndUser extends EndUser {

	@OneToMany(mappedBy = "createdBy")
	List<Notice> notices;

}
