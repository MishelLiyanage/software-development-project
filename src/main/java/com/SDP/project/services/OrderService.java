package com.SDP.project.services;

import com.SDP.project.DTOs.AllOrdersDto;
import com.SDP.project.DTOs.OrderCategoryData;
import com.SDP.project.DTOs.OrderRequestDTO;
import com.SDP.project.DTOs.UpdateOrderDto;
import com.SDP.project.DTOs.response.OrderResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OrderService {
    ResponseEntity<OrderResponseDto> placeOrder(OrderRequestDTO orderRequestDTO);
    List<AllOrdersDto> getAllOrders();
    UpdateOrderDto updateOrder(UpdateOrderDto updateOrderDTO);
    void deleteOrder(String orderId);
    List<OrderCategoryData> getOrderDistribution();
}
