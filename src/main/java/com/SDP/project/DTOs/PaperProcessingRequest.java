package com.SDP.project.DTOs;

import lombok.Data;

@Data
public class PaperProcessingRequest {
    private String grade;
    private String category;
    private int fromPaperNo;
    private int toPaperNo;
    private int sequenceNo;
}

