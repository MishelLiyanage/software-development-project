package com.SDP.project.controllers;

import com.SDP.project.DTOs.PaperNumberingDto;
import com.SDP.project.DTOs.response.NumberingCounterDetails;
import com.SDP.project.models.PaperNumbering;
import com.SDP.project.services.PaperNumberingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/paper-numbering")
public class PaperNumberingController {

    @Autowired
    private PaperNumberingService paperNumberingService;

    @PostMapping("/save")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public PaperNumbering saveInitialCounter(@RequestBody PaperNumberingDto paperNumbering) {
        return paperNumberingService.saveInitialCounter(paperNumbering);
    }

    @GetMapping("/getByGradeAndCategory")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EMPLOYEE')")
    public NumberingCounterDetails getByGradeAndCategory(
            @RequestParam String grade,
            @RequestParam String category) {
        return paperNumberingService.getCounterNumber(grade, category);
    }
}