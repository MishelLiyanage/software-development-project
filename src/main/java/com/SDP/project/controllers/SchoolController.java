package com.SDP.project.controllers;

import com.SDP.project.DTOs.SchoolDto;
import com.SDP.project.services.SchoolService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/school")
@RestController
public class SchoolController {

    @Autowired
    SchoolService schoolService;

    @PostMapping("/sign-up")
    public String registerSchool(@Valid @RequestBody SchoolDto schoolDto) {
        return schoolService.saveSchool(schoolDto);
    }

    @GetMapping("/sample")
    @PreAuthorize( "hasRole('ROLE_SCHOOL')")
    public String sampleSchool(){
        return "Hello I am a sample school";
    }
}
