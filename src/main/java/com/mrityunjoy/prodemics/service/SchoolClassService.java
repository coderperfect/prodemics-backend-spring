package com.mrityunjoy.prodemics.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mrityunjoy.prodemics.model.SchoolClass;
import com.mrityunjoy.prodemics.repository.SchoolClassRepository;

@Service
public class SchoolClassService {

    private final SchoolClassRepository repository;

    public SchoolClassService(SchoolClassRepository repository) {
        this.repository = repository;
    }

    public SchoolClass save(SchoolClass schoolClass) {
        return repository.save(schoolClass);
    }

    public List<SchoolClass> findAll() {
        return repository.findAll();
    }
}
