package com.SDP.project.services;

import com.SDP.project.DTOs.TaskDto;
import com.SDP.project.models.Task;

public interface TaskService {
    Task saveTask(TaskDto taskDto);
}

