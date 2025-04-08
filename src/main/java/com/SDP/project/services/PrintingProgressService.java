package com.SDP.project.services;

import com.SDP.project.DTOs.PrintingProgressDetailsDto;
import com.SDP.project.models.PrintingProgress;

public interface PrintingProgressService {
    void updateOrCreateProgress(PrintingProgressDetailsDto printingProgressDetailsDto);
    PrintingProgress getByTaskId(int taskId);
}
