package com.mrityunjoy.prodemics.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.Data;

@Data
@Entity
public class EndUser {
	@Id
	private String username;

	private String emailId;

	private String name;

	private String password;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST, targetEntity = Role.class)
	@JoinTable(name = "USER_ROLE", joinColumns = @JoinColumn(name = "USERNAME"), inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
	private List<Role> roles;
}
