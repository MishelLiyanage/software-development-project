package com.SDP.project.services;

import com.SDP.project.DTOs.PaperProcessingRequest;
import com.SDP.project.DTOs.response.PaperProcessingDetailsResponse;
import com.SDP.project.models.PaperProcessing;

public interface PaperProcessingService {
    PaperProcessing saveProcessing(PaperProcessingRequest request);
    PaperProcessingDetailsResponse getProcessingDetails(String grade, String category);
}
