package com.SDP.project.services;

import com.SDP.project.DTOs.DepartmentDto;

import java.util.List;

public interface DepartmentService {
    String createDepartment(DepartmentDto departmentDto);

    List<String> getAllDepartmentNames();
}
