package com.mrityunjoy.prodemics.controller;

import java.util.List;

import com.mrityunjoy.prodemics.service.SchoolClassService;
import org.springframework.web.bind.annotation.*;

import com.mrityunjoy.prodemics.model.SchoolClass;

@RestController
@RequestMapping("/api/classes")
public class SchoolClassController {

    private final SchoolClassService schoolClassService;

    public SchoolClassController(SchoolClassService schoolClassService) {
        this.schoolClassService = schoolClassService;
    }

    @GetMapping
    public List<SchoolClass> getAll() {
        return schoolClassService.findAll();
    }

    @PostMapping
    public SchoolClass create(@RequestBody SchoolClass schoolClass) {
        return schoolClassService.save(schoolClass);
    }
}
