package com.SDP.project.controllers;

import com.SDP.project.DTOs.EmployeeDto;
import com.SDP.project.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @PostMapping("/employee")
    public String registerEmployee(@RequestBody EmployeeDto employeeDto) {
        System.out.println("Registering new employee");
        return employeeService.saveEmployee(employeeDto);
    }
}
