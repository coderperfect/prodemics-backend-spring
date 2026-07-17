package com.mrityunjoy.prodemics.model;

import java.time.LocalDate;

import jakarta.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "notice", schema = "prodemics")
public class Notice extends BaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String title;

	private String description;

	private LocalDate createdAt;
}
