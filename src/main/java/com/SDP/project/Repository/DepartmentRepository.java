package com.SDP.project.Repository;

import com.SDP.project.models.Department;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    @EntityGraph(attributePaths = "employees")
    Department getDepartmentByName(String name);

}
