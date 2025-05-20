package com.SDP.project.controllers;

import com.SDP.project.DTOs.InventoryDto;
import com.SDP.project.DTOs.InventoryItemsDto;
import com.SDP.project.DTOs.ProcessOrderInventoryDto;
import com.SDP.project.models.Inventory;
import com.SDP.project.services.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/{taskId}")
    public ResponseEntity<Inventory> getInventoryByTaskId(@PathVariable int taskId) {
        Optional<Inventory> inventory = inventoryService.findByTaskId(taskId);
        return inventory != null ? (ResponseEntity<Inventory>) ResponseEntity.ok() : ResponseEntity.notFound().build();
    }

    @GetMapping("/levels")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EMPLOYEE')")
    public List<InventoryItemsDto> getInventoryLevels() {
        return inventoryService.getInventoryLevels();
    }

    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EMPLOYEE')")
    public ResponseEntity<List<ProcessOrderInventoryDto>> getAllInventory() {
        List<ProcessOrderInventoryDto> inventories = inventoryService.getAllInventory();
        return ResponseEntity.ok(inventories);
    }
}
