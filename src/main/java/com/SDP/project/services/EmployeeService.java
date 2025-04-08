package com.SDP.project.services;

import com.SDP.project.DTOs.EmployeeDto;
import com.SDP.project.DTOs.EmployeeInfoDto;

import java.util.List;

public interface EmployeeService {

    String saveEmployee(EmployeeDto employeeDto);
    List<EmployeeInfoDto> getEmployeeNamesWithDepartment();
}
