package com.SDP.project.DTOs;

import lombok.Data;

@Data
public class ProcessedOrderRequest {
    private ProcessedOrderDTO order;
    private ProcessedOrderSchoolDTO school;
}
