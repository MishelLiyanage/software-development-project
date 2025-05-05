package com.SDP.project.controllers;

import com.SDP.project.DTOs.ManageSchoolsDto;
import com.SDP.project.DTOs.SchoolDto;
import com.SDP.project.DTOs.UpdateSchoolDto;
import com.SDP.project.models.School;
import com.SDP.project.services.SchoolService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @GetMapping("/all")
    @PreAuthorize( "hasRole('ROLE_EMPLOYEE')")
    public List<ManageSchoolsDto> getAllSchools() {
        return schoolService.getAllSchools();
    }

    @PatchMapping("/updateSchool")
    @PreAuthorize("hasAnyRole('ROLE_EMPLOYEE', 'ROLE_ADMIN')")
    public ResponseEntity<School> updateSchool(@RequestBody UpdateSchoolDto updateSchoolDto) {
        return ResponseEntity.ok(schoolService.updateOrder(updateSchoolDto));
    }

    @DeleteMapping("/{schoolEmail}")
    @PreAuthorize("hasAnyRole('ROLE_EMPLOYEE', 'ROLE_ADMIN')")
    public ResponseEntity<Void> deleteSchool(@PathVariable String schoolEmail) {
        schoolService.deleteSchool(schoolEmail);
        return ResponseEntity.noContent().build();
    }
}
