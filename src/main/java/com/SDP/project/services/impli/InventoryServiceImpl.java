package com.SDP.project.services.impli;

import com.SDP.project.DTOs.InventoryDto;
import com.SDP.project.DTOs.InventoryItemsDto;
import com.SDP.project.DTOs.ProcessOrderInventoryDto;
import com.SDP.project.Repository.InventoryRepository;
import com.SDP.project.Repository.ModelPaperRepository;
import com.SDP.project.Repository.TaskRepository;
import com.SDP.project.models.Inventory;
import com.SDP.project.models.ModelPaper;
import com.SDP.project.services.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private ModelPaperRepository modelPaperRepository;

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

    public Optional<Inventory> findByTaskId(int taskId) {
        return inventoryRepository.findByTaskId(taskId);
    }

    @Override
    public List<InventoryItemsDto> getInventoryLevels() {
        List<Inventory> inventories = inventoryRepository.findAll();

        return inventories.stream().map(inv -> {
            int modelPaperId = inv.getModelPaperId(); // Assuming this is a field in your entity

            ModelPaper modelPaper = modelPaperRepository.findById(modelPaperId)
                    .orElse(null);

            String name = (modelPaper != null)
                    ? modelPaper.getGrade() + " " +
                    modelPaper.getCategory() + " " +
                    modelPaper.getPaperNo() + " " +
                    modelPaper.getPartNo()
                    : "Unknown Model Paper";

            return new InventoryItemsDto(name, inv.getQuantity());
        }).collect(Collectors.toList());
    }

    public List<ProcessOrderInventoryDto> getAllInventory() {
        List<Inventory> inventories = inventoryRepository.findAll();
        return convertToDTOList(inventories);
    }

    private List<ProcessOrderInventoryDto> convertToDTOList(List<Inventory> inventories) {
        return inventories.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private ProcessOrderInventoryDto convertToDTO(Inventory inventory) {
        int modelPaperId = inventory.getModelPaperId();

        Optional<ModelPaper> modelPaper = modelPaperRepository.findById(modelPaperId);
        return new ProcessOrderInventoryDto(
                modelPaper.get().getId(),
                modelPaper.get().getGrade(),
                modelPaper.get().getCategory(),
                modelPaper.get().getPaperNo(),
                modelPaper.get().getPartNo(),
                inventory.getQuantity()
        );
    }
}
