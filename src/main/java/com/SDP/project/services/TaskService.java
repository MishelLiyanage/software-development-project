package com.SDP.project.services;

import com.SDP.project.DTOs.TaskDto;
import com.SDP.project.models.Task;
import com.SDP.project.responses.TaskStatusUpdateResponse;

import java.util.List;

public interface TaskService {
    List<Task> getAllTasks();
    Task saveTask(TaskDto taskDto);
    TaskStatusUpdateResponse updateTaskStatus(int taskId, String status);
}

