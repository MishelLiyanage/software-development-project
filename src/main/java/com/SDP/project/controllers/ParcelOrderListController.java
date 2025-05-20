package com.SDP.project.controllers;

import com.SDP.project.DTOs.OrderDetailDto;
import com.SDP.project.services.ParcelOrderListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/parcelList")
@RestController
public class ParcelOrderListController {
    @Autowired
    private ParcelOrderListService parcelOrderListService;

    @PostMapping("/by-ids")
    public ResponseEntity<List<OrderDetailDto>> getOrderDetails(@RequestBody List<String> orderIds) {
        List<OrderDetailDto> details = parcelOrderListService.getOrderDetailsByIds(orderIds);
        return ResponseEntity.ok(details);
    }
}
