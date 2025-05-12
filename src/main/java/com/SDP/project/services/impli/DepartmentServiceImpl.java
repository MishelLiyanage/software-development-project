package com.SDP.project.services.impli;

import com.SDP.project.DTOs.DepartmentDto;
import com.SDP.project.Repository.DepartmentRepository;
import com.SDP.project.models.Department;
import com.SDP.project.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    public String createDepartment(DepartmentDto departmentDto){
        Department department = new Department(departmentDto.getName());
        departmentRepository.save(department);
        return "Department created successfully";
    }

    @Override
    public List<String> getAllDepartmentNames() {
        return departmentRepository.findAll()
                .stream()
                .map(Department::getName)
                .toList();
    }
}
