package com.mrityunjoy.prodemics.model;

import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Data
@Entity
@Table(name = "role", schema = "prodemics")
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;
	
	@ManyToMany(mappedBy = "roles", targetEntity = EndUser.class)
	private List<EndUser> endUsers;
}
