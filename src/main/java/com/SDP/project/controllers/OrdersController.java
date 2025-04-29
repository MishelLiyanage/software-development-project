package com.SDP.project.controllers;

import com.SDP.project.DTOs.OrderItemDTO;
import com.SDP.project.DTOs.OrderRequestDTO;
import com.SDP.project.DTOs.response.OrderResponseDto;
import com.SDP.project.services.OrderService;
import com.SDP.project.services.PaperSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/order")
@RestController
public class OrdersController {
    @Autowired
    private PaperSetService paperSetService;

    @Autowired
    private OrderService orderService;

    @PostMapping("/calculate-total")
    @PreAuthorize("hasAnyRole('ROLE_SCHOOL')")
    public ResponseEntity<Double> calculateTotalAmount(@RequestBody List<OrderItemDTO> items) {
        double total = 0;

        for (OrderItemDTO item : items) {
            double price = paperSetService.getPriceByName(item.getPublicationName());
            total += price * item.getQuantity();
        }

        return ResponseEntity.ok(total);
    }

    @PostMapping("/place-order")
    @PreAuthorize("hasAnyRole('ROLE_SCHOOL')")
    public ResponseEntity<OrderResponseDto> placeOrder(@RequestBody OrderRequestDTO orderRequestDTO) {
        return orderService.placeOrder(orderRequestDTO);
    }
}
