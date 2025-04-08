package com.SDP.project.Repository;

import com.SDP.project.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
//    List<Task> findByStatus(String status);
}
