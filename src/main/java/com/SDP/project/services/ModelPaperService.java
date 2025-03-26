package com.SDP.project.services;

import com.SDP.project.DTOs.ModelPaperDto;
import com.SDP.project.models.ModelPaper;

public interface ModelPaperService {
    ModelPaper saveModelPaper(ModelPaperDto modelPaperDto);
}
