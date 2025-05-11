package com.SDP.project.services;

import com.SDP.project.DTOs.*;
import com.SDP.project.DTOs.response.OrderResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OrderService {
    ResponseEntity<OrderResponseDto> placeOrder(OrderRequestDTO orderRequestDTO);
    List<AllOrdersDto> getAllOrders();
    UpdateOrderDto updateOrder(UpdateOrderDto updateOrderDTO);
    void deleteOrder(String orderId);
    List<OrderCategoryData> getOrderDistribution();
    List<MonthlyOrdersDataDto> getMonthlyProcessedOrders();
}
