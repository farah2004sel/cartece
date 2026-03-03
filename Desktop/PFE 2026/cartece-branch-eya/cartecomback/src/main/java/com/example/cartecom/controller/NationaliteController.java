package com.example.cartecom.controller;

import com.example.cartecom.domain.Nationalite;
import com.example.cartecom.repository.INationaliteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/nationalites")
@RequiredArgsConstructor
public class NationaliteController {

    private final INationaliteRepository nationaliteRepository;

    @GetMapping
    public List<Nationalite> getAllNationalites() {
        return nationaliteRepository.findAll();
    }
}