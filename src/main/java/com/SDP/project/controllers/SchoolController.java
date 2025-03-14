package com.SDP.project.controllers;

import com.SDP.project.DTOs.SchoolDto;
import com.SDP.project.services.SchoolService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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

    @PutMapping("/update-profile")
    @PreAuthorize("hasRole('ROLE_SCHOOL')")
    public ResponseEntity<Map<String, String>> updateSchoolProfile(@Valid @RequestBody SchoolDto schoolDto) {
        return schoolService.updateSchoolProfile(schoolDto);
    }
}
