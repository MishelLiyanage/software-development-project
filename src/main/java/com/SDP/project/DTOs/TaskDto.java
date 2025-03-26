package com.SDP.project.DTOs;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto {
    private int modelPaperId;
    private LocalDate startedDate;
    private LocalTime startedTime;
    private String status;
}
