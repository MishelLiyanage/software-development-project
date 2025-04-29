package com.SDP.project.controllers;

import com.SDP.project.DTOs.PaperSetDto;
import com.SDP.project.DTOs.response.PaperSetResponse;
import com.SDP.project.models.PaperSets;
import com.SDP.project.services.PaperSetService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/getIdByName")
    @PreAuthorize("hasAnyRole('ROLE_SCHOOL')")
    public ResponseEntity<Integer> getPaperSetIdByPublicationName(@RequestParam("publicationName") String publicationName) {
        System.out.println("Received publicationName = " + publicationName);

        Integer paperSetId = paperSetService.getPaperSetIdByPublicationName(publicationName);
        System.out.println("Found paperSetId = " + paperSetId);

//        if (paperSetId == null) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//        }
        return ResponseEntity.ok(paperSetId);
    }
}