package com.SDP.project.controllers;

import com.SDP.project.DTOs.SchoolDto;
import com.SDP.project.services.SchoolService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SchoolController {

    @Autowired
    SchoolService schoolService;

    @PostMapping("/school")
    public String registerSchool(@Valid @RequestBody SchoolDto schoolDto) {
        return schoolService.saveSchool(schoolDto);
    }
}
