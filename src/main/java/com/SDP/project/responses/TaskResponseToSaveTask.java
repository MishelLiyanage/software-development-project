package com.SDP.project.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TaskResponseToSaveTask {
    private boolean success;
    private int taskId;
}

