package com.SDP.project.controllers;

import com.SDP.project.DTOs.ProcessOrderDetailsDto;
import com.SDP.project.DTOs.ProcessedOrderRequest;
import com.SDP.project.services.ProcessOrderService;
import com.SDP.project.services.ProcessedOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/processOrder")
@RestController
public class ProcessOrderController {
    @Autowired
    private ProcessOrderService processOrderService;
    @Autowired
    private ProcessedOrderService processedOrderService;

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

    @PostMapping("/save")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EMPLOYEE')")
    public ResponseEntity<Map<String, String>> saveProcessedOrder(@RequestBody ProcessedOrderRequest request) {
        processedOrderService.saveProcessedOrder(request);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Processed order and school details saved successfully");
        return ResponseEntity.ok(response);
    }
}
