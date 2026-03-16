package com.mc.icmc.controller;

import com.mc.icmc.domain.Nationalite;
import com.mc.icmc.service.INationaliteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/nationalites")
@CrossOrigin(origins = "*")
public class NationaliteController {

    private final INationaliteService nationaliteService;

    public NationaliteController(INationaliteService nationaliteService) {
        this.nationaliteService = nationaliteService;
    }

    @GetMapping
    public List<Nationalite> getAllNationalites() {
        return nationaliteService.getAll();
    }

    @PostMapping
    public Nationalite create(@RequestBody Nationalite n) {
        return nationaliteService.create(n);
    }
}