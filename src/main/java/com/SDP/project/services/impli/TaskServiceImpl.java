package com.SDP.project.services.impli;

import com.SDP.project.DTOs.TaskDto;
import com.SDP.project.Repository.ModelPaperRepository;
import com.SDP.project.Repository.TaskRepository;
import com.SDP.project.models.ModelPaper;
import com.SDP.project.models.Task;
import com.SDP.project.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ModelPaperRepository modelPaperRepository;

    @Override
    public Task saveTask(TaskDto taskDto) {
        Optional<ModelPaper> modelPaper = modelPaperRepository.findById(Math.toIntExact(taskDto.getModelPaperId()));

        if (modelPaper.isPresent()) {
            Task task = new Task();
            task.setModelPaper(modelPaper.get());
            task.setCreatedDate(LocalDate.now());
            task.setCreatedTime(LocalTime.now());
            task.setStatus("To Do");

            return taskRepository.save(task);
        } else {
            throw new RuntimeException("Model Paper not found");
        }
    }
}
