package com.SDP.project.services;

import com.SDP.project.DTOs.InventoryDto;
import com.SDP.project.DTOs.InventoryItemsDto;
import com.SDP.project.DTOs.ProcessOrderInventoryDto;
import com.SDP.project.models.Inventory;

import java.util.List;
import java.util.Optional;

public interface InventoryService {
    Inventory saveInventory(InventoryDto inventoryDto);

    Optional<Inventory> findByTaskId(int taskId);

    List<InventoryItemsDto> getInventoryLevels();

    List<ProcessOrderInventoryDto> getAllInventory();
}
