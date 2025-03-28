package com.SDP.project.responses;

import com.SDP.project.models.Task;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class TaskResponse {
    private boolean success;
    private List<Task> tasks;
}

