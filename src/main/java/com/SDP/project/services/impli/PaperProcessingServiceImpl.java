package com.SDP.project.services.impli;

import com.SDP.project.DTOs.PaperProcessingRequest;
import com.SDP.project.DTOs.response.PaperProcessingDetailsResponse;
import com.SDP.project.Repository.PaperProcessingRepository;
import com.SDP.project.models.PaperProcessing;
import com.SDP.project.services.PaperProcessingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaperProcessingServiceImpl implements PaperProcessingService {
    @Autowired
    private PaperProcessingRepository paperProcessingRepository;

    public PaperProcessing saveProcessing(PaperProcessingRequest request) {
        PaperProcessing processing = new PaperProcessing();
        processing.setGrade(request.getGrade());
        processing.setCategory(request.getCategory());
        processing.setFromPaperNo(request.getFromPaperNo());
        processing.setToPaperNo(request.getToPaperNo());
        processing.setSequenceNo(request.getSequenceNo());

        return paperProcessingRepository.save(processing);
    }

    @Override
    public PaperProcessingDetailsResponse getProcessingDetails(String grade, String category) {
        var records = paperProcessingRepository.findByGradeAndCategory(grade, category);
        return new PaperProcessingDetailsResponse(records.getFromPaperNo(), records.getToPaperNo(), records.getSequenceNo());
    }
}
