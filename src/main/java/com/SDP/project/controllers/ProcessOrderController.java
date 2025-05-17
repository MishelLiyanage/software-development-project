package com.SDP.project.controllers;

import com.SDP.project.DTOs.ProcessOrderDetailsDto;
import com.SDP.project.services.ProcessOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/processOrder")
@RestController
public class ProcessOrderController {
    @Autowired
    private ProcessOrderService processOrderService;

    @GetMapping("/pending-scholarship-tamil")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EMPLOYEE')")
    public ResponseEntity<List<String>> getPendingScholarshipTamilOrders() {
        List<String> orderIds = processOrderService.getScholarshipTamilPendingOrderIds();
        return ResponseEntity.ok(orderIds);
    }

    @GetMapping("/order-details/{orderId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EMPLOYEE')")
    public ResponseEntity<ProcessOrderDetailsDto> getOrderDetails(@PathVariable String orderId) {
        return  processOrderService.getOrderDetails(orderId);
    }

}
