package com.SDP.project.DTOs;

import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProcessedOrderDTO {
    private String orderId;
    private String grade;
    private String category;
    private int lastCounterNumber;
    private int processedQuantity;
    private String sequenceNo;
    private String dateProcessed;
    private String timeProcessed;
    private int fromPaperNo;
    private int toPaperNo;
    private List<Integer> counterFromNumbers;
    private List<Integer> counterToNumbers;
}
