package com.SDP.project.services.impli;

import com.SDP.project.DTOs.PrintingProgressDetailsDto;
import com.SDP.project.Repository.PrintingProgressRepository;
import com.SDP.project.models.PrintingProgress;
import com.SDP.project.services.PrintingProgressService;
import com.SDP.project.shared.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PrintingProgressServiceImpl implements PrintingProgressService {

    @Autowired
    private PrintingProgressRepository printingProgressRepository;

    @Override
    public void updateOrCreateProgress(PrintingProgressDetailsDto printingProgressDetailsDto) {
        Optional<PrintingProgress> existing = printingProgressRepository.findByTaskId(printingProgressDetailsDto.getTaskId());

        PrintingProgress progress = existing.orElse(new PrintingProgress());
        progress.setTaskId(printingProgressDetailsDto.getTaskId());
        progress.setSubmittedDate(printingProgressDetailsDto.getSubmittedDate());
        progress.setDeadline(printingProgressDetailsDto.getDeadline());
        progress.setExpectedQuantity(printingProgressDetailsDto.getExpectedQuantity());
        progress.setRemainingToPrintQuantity(printingProgressDetailsDto.getRemainingToPrintQuantity());
        progress.setStarted(printingProgressDetailsDto.isStarted());
        progress.setSentToInventory(printingProgressDetailsDto.isSentToInventory());

        printingProgressRepository.save(progress);
    }

    @Override
    public PrintingProgress getByTaskId(int taskId) {
        return printingProgressRepository.findByTaskId(taskId)
                .orElseThrow(() -> new NotFoundException("Printing progress not found for task ID: " + taskId));
    }


}
