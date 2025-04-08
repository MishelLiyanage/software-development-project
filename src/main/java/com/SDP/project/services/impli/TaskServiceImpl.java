package com.SDP.project.services.impli;

import com.SDP.project.DTOs.TaskDetailsDto;
import com.SDP.project.DTOs.TaskDto;
import com.SDP.project.Repository.*;
import com.SDP.project.models.*;
import com.SDP.project.responses.TaskStatusUpdateResponse;
import com.SDP.project.services.TaskService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private PrintingProgressRepository printingProgressRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private TaskEmployeeAssignmentRepository taskEmployeeAssignmentRepository;

    @Autowired
    private TaskEmployeeAssignmentRepository assignmentRepository;

    @Autowired
    private ModelPaperRepository modelPaperRepository;

    @Override
    public Task saveTask(TaskDto taskDto) {
        Optional<ModelPaper> ModelPaper = modelPaperRepository.findById(taskDto.getModelPaper().getId());

        if (ModelPaper.isPresent()) {
            Task task = new Task();
            task.setModelPaper(ModelPaper.get()); // Get the actual ModelPaper object
            task.setCreatedDate(LocalDate.now());
            task.setCreatedTime(LocalTime.now());
            task.setStatus("To Do");

            return taskRepository.save(task);
        } else {
            throw new RuntimeException("Model Paper not found");
        }
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    @Transactional
    public TaskStatusUpdateResponse updateTaskStatus(int taskId, String status) {
        Optional<Task> optionalTask = taskRepository.findById(taskId);

        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            task.setStatus(status);
            taskRepository.save(task);
            return new TaskStatusUpdateResponse(true, "Task status updated successfully");
        } else {
            return new TaskStatusUpdateResponse(false, "Task not found");
        }
    }

    @Override
    public boolean assignEmployeeToTask(TaskDetailsDto taskDetailsDto) {
        Optional<Task> optionalTask = taskRepository.findById(taskDetailsDto.getTaskId());
        System.out.println(taskDetailsDto.getTaskId() + " " +
                taskDetailsDto.getAssignedDate() + " " +
                taskDetailsDto.getEmployeeId() + " " +
                taskDetailsDto.getDeadline());

        if (optionalTask.isPresent()) {
            // Check if assignment already exists for this taskId
            TaskEmployeeAssignment assignment = taskEmployeeAssignmentRepository.findByTaskId(taskDetailsDto.getTaskId());

            if (assignment == null) {
                // No existing assignment, create a new one
                assignment = new TaskEmployeeAssignment();
                assignment.setTaskId(taskDetailsDto.getTaskId());
            }

            // Set or update assignment details
            assignment.setAssignedDate(taskDetailsDto.getAssignedDate());
            assignment.setDeadline(taskDetailsDto.getDeadline());

            // Validate and set employee
            Optional<Employee> employee = employeeRepository.findById(taskDetailsDto.getEmployeeId());
            if (employee.isPresent()) {
                assignment.setAssignedEmployee(employee.get());
            } else {
                throw new RuntimeException("Employee not found");
            }

            // Save (create or update)
            taskEmployeeAssignmentRepository.save(assignment);
            return true;
        }
        return false;
    }

    @Override
    public TaskDetailsDto getAssignmentDetails(int taskId) {
        TaskEmployeeAssignment assignment = assignmentRepository.findByTaskId(taskId);

        if (assignment != null) {
            TaskDetailsDto dto = new TaskDetailsDto();
            dto.setTaskId(taskId);
            dto.setEmployeeId(assignment.getAssignedEmployee().getId());
            dto.setAssignedDate(assignment.getAssignedDate());
            dto.setDeadline(assignment.getDeadline());
            return dto;
        }

        return null;
    }
}