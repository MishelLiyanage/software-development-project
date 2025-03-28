package com.SDP.project.controllers;

import com.SDP.project.DTOs.TaskDto;
import com.SDP.project.responses.TaskResponse;
import com.SDP.project.responses.TaskResponseToSaveTask;
import com.SDP.project.models.Task;
import com.SDP.project.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("/")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EMPLOYEE')")
    public TaskResponseToSaveTask saveTask(@RequestBody TaskDto taskDTO) {
        Task task = taskService.saveTask(taskDTO);
        return new TaskResponseToSaveTask(true, task.getTaskId()); // Returning success and taskId
    }

    @GetMapping("/getTasks")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EMPLOYEE')")
    public TaskResponse getAllTasks() {
        List<Task> tasks = taskService.getAllTasks();
        boolean success = tasks != null && !tasks.isEmpty();
        return new TaskResponse(success, tasks);
    }
}
