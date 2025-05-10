package com.SDP.project.controllers;

import com.SDP.project.DTOs.EmployeeDto;
import com.SDP.project.DTOs.EmployeeInfoDto;
import com.SDP.project.services.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/employee")
@RestController
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @PostMapping("/sign-up")
    public String registerEmployee(@Valid @RequestBody EmployeeDto employeeDto) {
        return employeeService.saveEmployee(employeeDto);
    }


    @GetMapping("/names-with-department")
    @PreAuthorize( "hasAnyRole('ROLE_EMPLOYEE', 'ROLE_ADMIN')")
    public ResponseEntity<List<EmployeeInfoDto>> getEmployeeInfo() {
        return ResponseEntity.ok(employeeService.getEmployeeNamesWithDepartment());
    }
}
