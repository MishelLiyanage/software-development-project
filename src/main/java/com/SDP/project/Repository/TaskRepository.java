package com.SDP.project.Repository;

import com.SDP.project.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
    @Query("SELECT t.modelPaper.id FROM Task t WHERE t.taskId = :taskId")
    int findModelPaperIdByTaskId(@Param("taskId") Integer taskId);
}
