package com.SDP.project.controllers;

import com.SDP.project.DTOs.DepartmentDto;
import com.SDP.project.Repository.DepartmentRepository;
import com.SDP.project.services.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/department")
@RestController
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;

    @PostMapping("/")
    @PreAuthorize( "hasAnyRole('ROLE_ADMIN')")
    public String createDepartment(@Valid @RequestBody DepartmentDto departmentDto) {
        return departmentService.createDepartment(departmentDto);
    }
}