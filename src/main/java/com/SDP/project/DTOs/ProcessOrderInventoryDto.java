package com.SDP.project.DTOs;

import lombok.*;

import java.time.LocalDateTime;

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
