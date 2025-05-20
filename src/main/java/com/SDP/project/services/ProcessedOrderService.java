package com.SDP.project.services;

import com.SDP.project.DTOs.ProcessedOrderRequest;

public interface ProcessedOrderService {
    void saveProcessedOrder(ProcessedOrderRequest request);
}
