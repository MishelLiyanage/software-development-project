package com.SDP.project.Repository;

import com.SDP.project.models.TaskEmployeeAssignment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskEmployeeAssignmentRepository extends JpaRepository<TaskEmployeeAssignment, Integer> {
    TaskEmployeeAssignment findByTaskId(int taskId);
}


