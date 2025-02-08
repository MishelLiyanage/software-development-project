package com.SDP.project.controllers;

import com.SDP.project.DTOs.EmployeeDto;
import com.SDP.project.services.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/employee")
@RestController
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @PostMapping("/sign-up")
    public String registerEmployee(@Valid @RequestBody EmployeeDto employeeDto) {
        return employeeService.saveEmployee(employeeDto);
    }

    @GetMapping("/sample")
    @PreAuthorize( "hasAnyRole('EMPLOYEE')")
    public String sampleEmployee(){
        return "Hello I am a sample employee";
    }
}
