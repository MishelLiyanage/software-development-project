package com.SDP.project.controllers;

import com.SDP.project.DTOs.ModelPaperDto;
import com.SDP.project.services.ModelPaperService;
import jakarta.validation.Valid;
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
    @PreAuthorize( "hasRole('ROLE_ADMIN')")
    public String saveModelPaper(@Valid @RequestBody ModelPaperDto modelPaperDto) {
        return modelPaperService.saveModelPaper(modelPaperDto);
    }

}
