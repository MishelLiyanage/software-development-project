package com.SDP.project.DTOs;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InventoryItemsDto {
    private String modelPaperName;
    private int quantity;
}
