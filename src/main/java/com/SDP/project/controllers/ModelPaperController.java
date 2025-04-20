package com.SDP.project.controllers;

import com.SDP.project.DTOs.EmployeeInfoDto;
import com.SDP.project.DTOs.ModelPaperDto;
import com.SDP.project.DTOs.PublicationsDto;
import com.SDP.project.DTOs.response.ModelPaperResponse;
import com.SDP.project.models.ModelPaper;
import com.SDP.project.services.ModelPaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/modelpaper")
@RestController
public class ModelPaperController {
    @Autowired
    ModelPaperService modelPaperService;

    @PostMapping("/")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EMPLOYEE')")
    public ModelPaperResponse saveModelPaper(@RequestBody ModelPaperDto modelPaperDTO) {
        System.out.println(modelPaperDTO);
        ModelPaper modelPaper = modelPaperService.saveModelPaper(modelPaperDTO);
        return new ModelPaperResponse(true, modelPaper); // Return success and modelPaperId
    }

    @GetMapping("/publications")
    @PreAuthorize( "hasAnyRole('ROLE_SCHOOL')")
    public ResponseEntity<List<PublicationsDto>> getPublications() {
        return ResponseEntity.ok(modelPaperService.getGradeWithModelPaperCategory());
    }
}
