package com.SDP.project.controllers;

import com.SDP.project.DTOs.PaperProcessingRequest;
import com.SDP.project.DTOs.response.PaperProcessingDetailsResponse;
import com.SDP.project.models.PaperProcessing;
import com.SDP.project.services.PaperProcessingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/paper-processing")
@RestController
public class PaperProcessingController {
    @Autowired
    private PaperProcessingService paperProcessingService;

    @PostMapping("/")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<PaperProcessing> submitProcessing(@RequestBody PaperProcessingRequest request) {
        PaperProcessing saved = paperProcessingService.saveProcessing(request);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/getByGradeAndCategory")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EMPLOYEE')")
    public PaperProcessingDetailsResponse getPaperProcessingDetails(
            @RequestParam String grade,
            @RequestParam String category) {
        return paperProcessingService.getProcessingDetails(grade, category);
    }
}
