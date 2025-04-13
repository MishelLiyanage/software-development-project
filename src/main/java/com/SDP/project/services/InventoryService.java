package com.SDP.project.services;

import com.SDP.project.DTOs.InventoryDto;
import com.SDP.project.models.Inventory;

public interface InventoryService {
    Inventory saveInventory(InventoryDto inventoryDto);
}
