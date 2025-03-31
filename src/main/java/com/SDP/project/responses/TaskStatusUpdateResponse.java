package com.SDP.project.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TaskStatusUpdateResponse {
    private boolean success;
    private String message;
}
