package com.SDP.project.services;

import com.SDP.project.DTOs.EmployeeDto;
import com.SDP.project.DTOs.EmployeeInfoDto;
import com.SDP.project.DTOs.response.EmployeeRegistrationResponseDto;

import java.util.List;

public interface EmployeeService {

    EmployeeRegistrationResponseDto saveEmployee(EmployeeDto employeeDto);
    List<EmployeeInfoDto> getEmployeeNamesWithDepartment();
}
