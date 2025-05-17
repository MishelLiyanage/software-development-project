package com.SDP.project.services;

import com.SDP.project.DTOs.ProcessOrderDetailsDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProcessOrderService {
    List<String> getScholarshipTamilPendingOrderIds();
    ResponseEntity<ProcessOrderDetailsDto> getOrderDetails(String orderId);
}
