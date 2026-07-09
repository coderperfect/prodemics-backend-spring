package com.mrityunjoy.prodemics.model;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "end_user", schema = "prodemics")
@Inheritance(strategy = InheritanceType.JOINED)
public class EndUser {
	@Id
	private String username;

	private String emailId;

	private String name;

	@JsonIgnore
	private String password;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST, targetEntity = Role.class)
	@JoinTable(name = "USER_ROLE", schema = "prodemics", joinColumns = @JoinColumn(name = "USERNAME"), inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
	@JsonIgnore
	private List<Role> roles;
}
