package com.mc.icmc.controller;

import com.mc.icmc.domain.Commercant;
import com.mc.icmc.dto.response.CommercantResponse;
import com.mc.icmc.service.ICommercantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/commercants")
@RequiredArgsConstructor
public class CommercantController {

    private final ICommercantService commercantService;

    @PostMapping
    public Commercant create(@RequestBody Commercant commercant) {
        return commercantService.save(commercant);
    }

    @GetMapping
    public List<CommercantResponse> getAll() {
        return commercantService.findAllResponse();
    }

    @GetMapping("/{id}")
    public Commercant getById(@PathVariable Long id) {
        return commercantService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        commercantService.delete(id);
    }
}