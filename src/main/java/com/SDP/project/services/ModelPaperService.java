package com.SDP.project.services;

import com.SDP.project.DTOs.ModelPaperDto;
import com.SDP.project.DTOs.PublicationsDto;
import com.SDP.project.models.ModelPaper;

import java.util.List;

public interface ModelPaperService {
    ModelPaper saveModelPaper(ModelPaperDto modelPaperDto);

    List<PublicationsDto> getGradeWithModelPaperCategory();
}
