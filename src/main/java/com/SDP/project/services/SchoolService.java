package com.SDP.project.services;

import com.SDP.project.DTOs.ManageSchoolsDto;
import com.SDP.project.DTOs.SchoolDto;
import com.SDP.project.DTOs.UpdateSchoolDto;
import com.SDP.project.models.School;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface SchoolService {
    String saveSchool(SchoolDto schoolDto);

    ResponseEntity<Map<String, String>> updateSchoolProfile(SchoolDto schoolDto);

    List<ManageSchoolsDto> getAllSchools();

    School updateOrder(UpdateSchoolDto updateSchoolDto);

    void deleteSchool(String schoolEmail);
}
