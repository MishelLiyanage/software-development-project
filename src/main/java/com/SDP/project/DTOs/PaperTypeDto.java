package com.SDP.project.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PaperTypeDto {
    @JsonProperty("paper_type")
    private String paperType;

    @JsonProperty("department_name")
    private String departmentName;
}
