package com.SDP.project.controllers;

import com.SDP.project.DTOs.TaskDetailsDto;
import com.SDP.project.DTOs.TaskDto;
import com.SDP.project.responses.TaskResponse;
import com.SDP.project.responses.TaskResponseToSaveTask;
import com.SDP.project.models.Task;
import com.SDP.project.responses.TaskStatusUpdateResponse;
import com.SDP.project.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
        System.out.println(tasks);
        boolean success = tasks != null && !tasks.isEmpty();
        return new TaskResponse(success, tasks);
    }

    @GetMapping("/assignment/{taskId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EMPLOYEE')")
    public ResponseEntity<TaskDetailsDto> getTaskAssignment(@PathVariable int taskId) {
        TaskDetailsDto assignment = taskService.getAssignmentDetails(taskId);
        if (assignment != null) {
            return ResponseEntity.ok(assignment);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{taskId}/status")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EMPLOYEE')")
    public ResponseEntity<TaskStatusUpdateResponse> updateTaskStatus(
            @PathVariable int taskId,
            @RequestParam String status) {
        System.out.println(taskId + " " + status);
        TaskStatusUpdateResponse response = taskService.updateTaskStatus(taskId, status);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/assign")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EMPLOYEE')")
    public ResponseEntity<TaskStatusUpdateResponse> assignEmployeeToTask(@RequestBody TaskDetailsDto taskDetailsDto) {
        System.out.println(taskDetailsDto.getEmployeeId());
        boolean success = taskService.assignEmployeeToTask(taskDetailsDto);

        if (success) {
            return ResponseEntity.ok(new TaskStatusUpdateResponse(true, "Employee assigned to task successfully."));
        } else {
            return ResponseEntity.badRequest().body(new TaskStatusUpdateResponse(false, "Failed to assign employee to task."));
        }
    }
}
