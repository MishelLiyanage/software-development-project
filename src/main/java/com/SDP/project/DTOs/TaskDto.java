package com.SDP.project.DTOs;

import com.SDP.project.models.ModelPaper;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto {
    private ModelPaper modelPaper;
    private LocalDate startedDate;
    private LocalTime startedTime;
    private String status;
}
