package com.mrityunjoy.prodemics.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity
public class EndUser {
	@Id
	private String username;

	private String emailId;

	private String name;

	private String password;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST, targetEntity = Role.class)
	@JoinColumn(name = "role_id", referencedColumnName = "id", nullable = true)
	private Role role;
}
