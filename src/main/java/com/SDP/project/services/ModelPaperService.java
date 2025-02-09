package com.SDP.project.services;

import com.SDP.project.DTOs.ModelPaperDto;
import jakarta.validation.Valid;

public interface ModelPaperService {
    String saveModelPaper(ModelPaperDto modelPaperDto);
}
