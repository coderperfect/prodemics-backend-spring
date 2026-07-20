package com.mrityunjoy.prodemics.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mrityunjoy.prodemics.model.EndUser;

@Repository
public interface EndUserRepository extends JpaRepository<EndUser, String> {
	Optional<EndUser> findByUsername(String username);
}
