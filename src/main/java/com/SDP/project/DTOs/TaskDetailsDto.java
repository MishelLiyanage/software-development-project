package com.SDP.project.DTOs;

import com.SDP.project.models.Employee;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskDetailsDto {
    private int taskId;
    private LocalDate assignedDate;
    private LocalDate deadline;
    private int employeeId;
}
