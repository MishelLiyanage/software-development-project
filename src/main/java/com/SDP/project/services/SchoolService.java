package com.SDP.project.services;

import com.SDP.project.DTOs.SchoolDto;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface SchoolService {
    String saveSchool(SchoolDto schoolDto);

    ResponseEntity<Map<String, String>> updateSchoolProfile(SchoolDto schoolDto);
}
