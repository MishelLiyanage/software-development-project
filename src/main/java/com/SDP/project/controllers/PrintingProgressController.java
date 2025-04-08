package com.SDP.project.controllers;

import com.SDP.project.DTOs.PrintingProgressDetailsDto;
import com.SDP.project.models.PrintingProgress;
import com.SDP.project.services.PrintingProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/printing-progress")
public class PrintingProgressController {
    @Autowired
    private PrintingProgressService printingProgressService;

    @PutMapping("/update-printing")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EMPLOYEE')")
    public ResponseEntity<Map<String, String>> updateOrCreatePrintingProgress(@RequestBody PrintingProgressDetailsDto printingProgressDto) {
        printingProgressService.updateOrCreateProgress(printingProgressDto);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Printing progress updated or created successfully.");

        return ResponseEntity.ok(response);
    }


    @GetMapping("/{taskId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EMPLOYEE')")
    public ResponseEntity<PrintingProgress> getPrintingTask(@PathVariable int taskId) {
        PrintingProgress progress = printingProgressService.getByTaskId(taskId);
        return ResponseEntity.ok(progress);
    }
}
