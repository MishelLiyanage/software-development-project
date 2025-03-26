package com.SDP.project.services.impli;

import com.SDP.project.DTOs.ModelPaperDto;
import com.SDP.project.Repository.ModelPaperRepository;
import com.SDP.project.models.ModelPaper;
import com.SDP.project.services.ModelPaperService;
import com.SDP.project.shared.exceptions.RecordAlreadyExistException;
import org.springframework.stereotype.Service;

@Service
public class ModelPaperServiceImpl implements ModelPaperService {
    private final ModelPaperRepository modelPaperRepository;

    public ModelPaperServiceImpl(ModelPaperRepository modelPaperRepository) {
        this.modelPaperRepository = modelPaperRepository;
    }

    public ModelPaper saveModelPaper(ModelPaperDto modelPaperDto){
        isModelPaperExist(modelPaperDto);

        ModelPaper modelPaper = new ModelPaper();
        modelPaper.setGrade(modelPaperDto.getGrade());
        modelPaper.setCategory(modelPaperDto.getCategory());
        modelPaper.setPaperNo(modelPaperDto.getPaperNo());
        modelPaper.setPartNo(modelPaperDto.getPartNo());

        return modelPaperRepository.save(modelPaper);
    }

    private void isModelPaperExist(ModelPaperDto modelPaperDto) {
        boolean isModelPaperExist = modelPaperRepository.existsModelPaper(
                modelPaperDto.getGrade(),
                modelPaperDto.getPaperNo(),
                modelPaperDto.getPartNo()
        );

        System.out.println("Model Paper Exists: " + isModelPaperExist); // Debugging log

        if (isModelPaperExist) {
            throw new RecordAlreadyExistException("Model paper already exists.");
        }
    }
}
