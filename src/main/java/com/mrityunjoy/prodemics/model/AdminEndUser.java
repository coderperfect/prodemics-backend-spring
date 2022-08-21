package com.mrityunjoy.prodemics.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class AdminEndUser extends EndUser {

	@OneToMany(mappedBy = "createdBy")
	List<Notice> notices;

}
