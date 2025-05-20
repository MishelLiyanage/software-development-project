package com.SDP.project.DTOs;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProcessOrderInventoryDto {
    private int modelPaperId;
    private String grade;
    private String category;
    private String paperNo;
    private String partNo;
    private Integer quantity;
}
