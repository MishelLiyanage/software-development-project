package com.SDP.project.controllers;

import com.SDP.project.DTOs.PaperTypeDto;
import com.SDP.project.services.PaperTypeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/papertype")
@RestController
public class PaperTypeController {
    @Autowired
    PaperTypeService paperTypeService;

    @PostMapping("/")
    @PreAuthorize( "hasRole('ROLE_ADMIN')")
    public String saveModelPaper(@Valid @RequestBody PaperTypeDto paperTypeDto) {
        return paperTypeService.savePaperType(paperTypeDto);
    }
}
