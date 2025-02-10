package com.SDP.project.services.impli;

import com.SDP.project.DTOs.DepartmentDto;
import com.SDP.project.Repository.DepartmentRepository;
import com.SDP.project.models.Department;
import com.SDP.project.services.DepartmentService;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public String createDepartment(DepartmentDto departmentDto){
        Department department = new Department(departmentDto.getName());
        departmentRepository.save(department);
        return "Department created successfully";
    }
}
