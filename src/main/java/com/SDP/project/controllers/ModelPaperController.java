package com.SDP.project.controllers;

import com.SDP.project.DTOs.ModelPaperDto;
import com.SDP.project.DTOs.response.ModelPaperResponse;
import com.SDP.project.models.ModelPaper;
import com.SDP.project.services.ModelPaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/modelpaper")
@RestController
public class ModelPaperController {
    @Autowired
    ModelPaperService modelPaperService;

    @PostMapping("/")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EMPLOYEE')")
    public ModelPaperResponse saveModelPaper(@RequestBody ModelPaperDto modelPaperDTO) {
        ModelPaper modelPaper = modelPaperService.saveModelPaper(modelPaperDTO);
        return new ModelPaperResponse(true, modelPaper.getId()); // Return success and modelPaperId
    }
}
