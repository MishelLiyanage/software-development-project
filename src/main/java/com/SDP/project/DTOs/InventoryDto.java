package com.SDP.project.DTOs;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InventoryDto {
    private int taskId;
    private int quantity;
}
