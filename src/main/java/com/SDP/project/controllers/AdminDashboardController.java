package com.SDP.project.controllers;

import com.SDP.project.DTOs.AdminStatDto;
import com.SDP.project.services.AdminDashboardStatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/adminDashboard")
@RestController
public class AdminDashboardController {
    @Autowired
    private AdminDashboardStatService adminDashboardStatService;

    @GetMapping("/stats")
    @PreAuthorize( "hasAnyRole('ROLE_ADMIN', 'ROLE_EMPLOYEE')")
    public AdminStatDto getDashboardStat(){
        return adminDashboardStatService.getDashboardStat();
    }
}
