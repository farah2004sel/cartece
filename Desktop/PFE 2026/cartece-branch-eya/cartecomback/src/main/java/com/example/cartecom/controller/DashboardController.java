package com.example.cartecom.controller;

import com.example.cartecom.domain.Users;
import com.example.cartecom.dto.response.DashboardResponse;
import com.example.cartecom.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.web.bind.annotation.GetMapping;
import org.springframework.web.web.bind.annotation.RequestMapping;
import org.springframework.web.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@RequestMapping("/api/commercant/dashboard")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping
    public DashboardResponse getDashboard(@AuthenticationPrincipal Users user) {
        // Since we are using Users directly as the commercant
        return dashboardService.getDashboard(
                user.getId()
        );
    }
}
