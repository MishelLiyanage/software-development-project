package com.SDP.project.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class ModelPaperDto {
    @NotNull(message = "Grade is required")
    private String grade;

    @NotNull(message = "Category is required")
    private String category;

    @NotNull(message = "Paper number is required")
    @JsonProperty("paper_no")
    private String paperNo;

    @NotNull(message = "Paper part number is required")
    @Pattern(regexp = "1|2", message = "Part number must be either 1 or 2")
    @JsonProperty("part_no")
    private String partNo;

    private String description;
}
