package com.SDP.project.services;

import com.SDP.project.DTOs.InventoryDto;
import com.SDP.project.models.Inventory;

import java.util.Optional;

public interface InventoryService {
    Inventory saveInventory(InventoryDto inventoryDto);

    Optional<Inventory> findByTaskId(int taskId);
}
