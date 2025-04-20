package com.SDP.project.controllers;

import com.SDP.project.DTOs.PaperSetDto;
import com.SDP.project.DTOs.response.PaperSetResponse;
import com.SDP.project.models.PaperSets;
import com.SDP.project.services.PaperSetService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/paperset")
@RestController
public class PaperSetsController {
    @Autowired
    PaperSetService paperSetService;

    @PostMapping("/")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EMPLOYEE')")
    public PaperSetResponse savePaperSet(@Valid @RequestBody PaperSetDto paperSetDto) {
        PaperSets paperSets = paperSetService.savePaperType(paperSetDto);
        return new PaperSetResponse(true, paperSets);
    }
}