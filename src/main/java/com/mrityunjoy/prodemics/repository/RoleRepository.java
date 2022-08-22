package com.mrityunjoy.prodemics.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mrityunjoy.prodemics.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

	public List<Role> findByName(String name);
}
