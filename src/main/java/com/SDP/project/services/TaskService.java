package com.SDP.project.services;

import com.SDP.project.DTOs.TaskDto;
import com.SDP.project.models.Task;

import java.util.List;

public interface TaskService {
    List<Task> getAllTasks();
    Task saveTask(TaskDto taskDto);
}

