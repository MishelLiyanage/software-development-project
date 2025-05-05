package com.SDP.project.controllers;

import com.SDP.project.DTOs.AllOrdersDto;
import com.SDP.project.DTOs.OrderItemDTO;
import com.SDP.project.DTOs.OrderRequestDTO;
import com.SDP.project.DTOs.UpdateOrderDto;
import com.SDP.project.DTOs.response.OrderResponseDto;
import com.SDP.project.services.OrderService;
import com.SDP.project.services.PaperSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/getAllOrders")
    @PreAuthorize("hasAnyRole('ROLE_EMPLOYEE', 'ROLE_ADMIN')")
    public ResponseEntity<List<AllOrdersDto>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @PatchMapping("/updateOrder")
    @PreAuthorize("hasAnyRole('ROLE_EMPLOYEE', 'ROLE_ADMIN')")
    public ResponseEntity<UpdateOrderDto> updateOrder(@RequestBody UpdateOrderDto updateOrderDTO) {
        return ResponseEntity.ok(orderService.updateOrder(updateOrderDTO));
    }

    @DeleteMapping("/{orderId}")
    @PreAuthorize("hasAnyRole('ROLE_EMPLOYEE', 'ROLE_ADMIN')")
    public ResponseEntity<Void> deleteOrder(@PathVariable String orderId) {
        orderService.deleteOrder(orderId);
        return ResponseEntity.noContent().build();
    }
}
