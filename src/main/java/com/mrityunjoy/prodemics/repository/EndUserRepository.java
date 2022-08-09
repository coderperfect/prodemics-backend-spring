package com.mrityunjoy.prodemics.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mrityunjoy.prodemics.model.EndUser;

@Repository
public interface EndUserRepository extends CrudRepository<EndUser, String> {

}
