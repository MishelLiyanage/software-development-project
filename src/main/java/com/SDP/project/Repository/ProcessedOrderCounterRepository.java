package com.SDP.project.Repository;

import com.SDP.project.models.ProcessedOrderCounter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProcessedOrderCounterRepository extends JpaRepository<ProcessedOrderCounter, Integer> {
}
