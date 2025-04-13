package com.SDP.project.controllers;

import com.SDP.project.DTOs.InventoryDto;
import com.SDP.project.models.Inventory;
import com.SDP.project.services.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @PostMapping("/add")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EMPLOYEE')")
    public ResponseEntity<Inventory> addInventory(@RequestBody InventoryDto inventoryDto) {
        System.out.println(inventoryDto);
        Inventory savedInventory = inventoryService.saveInventory(inventoryDto);
        return ResponseEntity.ok(savedInventory);
    }
}
