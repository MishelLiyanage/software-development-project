package com.SDP.project.controllers;

import com.SDP.project.DTOs.OrderItemDTO;
import com.SDP.project.models.PaperSetPrice;
import com.SDP.project.services.ModelPaperService;
import com.SDP.project.services.PaperSetService;
import com.SDP.project.services.impli.ModelPaperServiceImpl;
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
    private ModelPaperService modelPaperService;

    @Autowired
    private PaperSetService paperSetService;

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

}
