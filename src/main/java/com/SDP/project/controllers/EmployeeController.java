package com.SDP.project.controllers;

import com.SDP.project.DTOs.EmployeeDto;
import com.SDP.project.services.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/employee")
@RestController
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;


    @PostMapping("/sign-up")
    public String registerEmployee(@Valid @RequestBody EmployeeDto employeeDto) {

        return employeeService.saveEmployee(employeeDto);
    }
}
