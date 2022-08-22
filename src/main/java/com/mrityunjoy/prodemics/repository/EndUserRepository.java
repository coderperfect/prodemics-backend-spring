package com.mrityunjoy.prodemics.repository;

import java.util.Arrays;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mrityunjoy.prodemics.model.EndUser;
import com.mrityunjoy.prodemics.model.Role;

@Repository
@Transactional
public class EndUserRepository {

	private EntityManager entityManager;
	private RoleRepository roleRepository;
	
	@Autowired
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Autowired
	public void setRoleReporsitory(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}
	
	public Optional<EndUser> getByUsername(String username) {
		return Optional.ofNullable(entityManager.find(EndUser.class, username));
	}
	
	public EndUser save(EndUser endUser) {
		entityManager.persist(endUser);
		return entityManager.find(EndUser.class, endUser.getUsername());
	}
	
	public EndUser saveWithRole(EndUser endUser, String roleName) {
		Role role = roleRepository.findByName(roleName).get(0);
		endUser.setRoles(Arrays.asList(role));
		entityManager.persist(endUser);
		return entityManager.find(EndUser.class, endUser.getUsername());
	}
	
	public EndUser setPassword(String username, String password) {
		EndUser endUser = entityManager.find(EndUser.class, username);
		endUser.setPassword(password);
		entityManager.persist(endUser);
		return endUser;
	}
}
