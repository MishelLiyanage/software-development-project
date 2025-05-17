package com.SDP.project.controllers;

import com.SDP.project.DTOs.PaperNumberingDto;
import com.SDP.project.models.PaperNumbering;
import com.SDP.project.services.PaperNumberingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/paper-numbering")
public class PaperNumberingController {

    @Autowired
    private PaperNumberingService paperNumberingService;

    @PostMapping("/save")
    public PaperNumbering saveInitialCounter(@RequestBody PaperNumberingDto paperNumbering) {
        return paperNumberingService.saveInitialCounter(paperNumbering);
    }
}