package com.SDP.project.Repository;

import com.SDP.project.models.ProcessedOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProcessedOrderRepository extends JpaRepository<ProcessedOrder, Integer> {}
