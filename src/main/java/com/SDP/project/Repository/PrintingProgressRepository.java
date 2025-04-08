package com.SDP.project.Repository;

import com.SDP.project.models.PrintingProgress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PrintingProgressRepository  extends JpaRepository<PrintingProgress, Integer> {
    Optional<PrintingProgress> findByTaskId(int taskId);

}
