package com.SDP.project.DTOs.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class SchoolOrderResponseDto {
    private String orderId;
    private List<String> orderItems;
    private String date;
    private String paymentStatus;
    private String orderStatus;
    private double amount;
}

