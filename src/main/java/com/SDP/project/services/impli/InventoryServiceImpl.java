package com.SDP.project.services.impli;

import com.SDP.project.DTOs.InventoryDto;
import com.SDP.project.Repository.InventoryRepository;
import com.SDP.project.Repository.TaskRepository;
import com.SDP.project.models.Inventory;
import com.SDP.project.models.ModelPaper;
import com.SDP.project.services.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;
    @Autowired
    private TaskRepository taskRepository;

    @Override
    public Inventory saveInventory(InventoryDto inventoryDto) {
        int modelPaperId  = taskRepository.findModelPaperIdByTaskId(inventoryDto.getTaskId());

        Optional<Inventory> existingInventoryOpt = inventoryRepository.findByTaskId(inventoryDto.getTaskId());

        Inventory inventory;
        if (existingInventoryOpt.isPresent()) {
            // Update existing record
            inventory = existingInventoryOpt.get();
            inventory.setQuantity(inventoryDto.getQuantity());
        } else {
            // Create new record
            inventory = new Inventory();
            inventory.setTaskId(inventoryDto.getTaskId());
            inventory.setModelPaperId(modelPaperId);
            inventory.setQuantity(inventoryDto.getQuantity());
        }

        return inventoryRepository.save(inventory);
    }
}
