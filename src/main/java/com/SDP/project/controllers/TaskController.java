package com.SDP.project.controllers;

import com.SDP.project.DTOs.TaskDto;
import com.SDP.project.DTOs.response.TaskResponse;
import com.SDP.project.models.Task;
import com.SDP.project.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("/")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EMPLOYEE')")
    public TaskResponse saveTask(@RequestBody TaskDto taskDTO) {
        Task task = taskService.saveTask(taskDTO);
        return new TaskResponse(true, task.getTaskId()); // Returning success and taskId
    }
}
