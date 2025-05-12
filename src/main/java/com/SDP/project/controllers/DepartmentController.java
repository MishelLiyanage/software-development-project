package com.SDP.project.controllers;

import com.SDP.project.DTOs.DepartmentDto;
import com.SDP.project.services.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/all")
    @PreAuthorize( "hasAnyRole('ROLE_ADMIN')")
    public List<String> getDepartments() {
        return departmentService.getAllDepartmentNames();
    }
}