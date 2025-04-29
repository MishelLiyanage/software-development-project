package com.SDP.project.services;

import com.SDP.project.DTOs.OrderRequestDTO;
import com.SDP.project.DTOs.response.OrderResponseDto;
import org.springframework.http.ResponseEntity;

public interface OrderService {
    ResponseEntity<OrderResponseDto> placeOrder(OrderRequestDTO orderRequestDTO);
}
