package com.mrityunjoy.prodemics.repository;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mrityunjoy.prodemics.model.EndUser;

@Repository
@Transactional
public class EndUserRepository {
	private EntityManager entityManager;
	
	@Autowired
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public Optional<EndUser> getByUsername(String username) {
		return Optional.ofNullable(entityManager.find(EndUser.class, username));
	}
	
	public EndUser save(EndUser endUser) {
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
