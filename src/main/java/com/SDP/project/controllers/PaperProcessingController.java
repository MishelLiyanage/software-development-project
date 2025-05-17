package com.SDP.project.controllers;

import com.SDP.project.DTOs.PaperProcessingRequest;
import com.SDP.project.models.PaperProcessing;
import com.SDP.project.services.PaperProcessingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/paper-processing")
@RestController
public class PaperProcessingController {
    @Autowired
    private PaperProcessingService paperProcessingService;

    @PostMapping("/")
    public ResponseEntity<PaperProcessing> submitProcessing(@RequestBody PaperProcessingRequest request) {
        PaperProcessing saved = paperProcessingService.saveProcessing(request);
        return ResponseEntity.ok(saved);
    }
}
