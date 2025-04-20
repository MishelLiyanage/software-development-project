package com.SDP.project.services.impli;

import com.SDP.project.DTOs.ModelPaperDto;
import com.SDP.project.DTOs.PublicationsDto;
import com.SDP.project.Repository.ModelPaperRepository;
import com.SDP.project.Repository.PapersetRepository;
import com.SDP.project.models.ModelPaper;
import com.SDP.project.services.ModelPaperService;
import com.SDP.project.shared.exceptions.RecordAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ModelPaperServiceImpl implements ModelPaperService {
    @Autowired
    private  ModelPaperRepository modelPaperRepository;

    @Autowired
    private PapersetRepository papersetRepository;

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
                modelPaperDto.getCategory(),
                modelPaperDto.getPaperNo(),
                modelPaperDto.getPartNo()
        );

        System.out.println("Model Paper Exists: " + isModelPaperExist); // Debugging log

        if (isModelPaperExist) {
            throw new RecordAlreadyExistException("Model paper already exists.");
        }
    }

    @Override
    public List<PublicationsDto> getGradeWithModelPaperCategory() {
        return modelPaperRepository.findAll().stream()
                .map(mp -> new PublicationsDto(mp.getGrade() + " " + mp.getCategory()))
                .distinct()
                .collect(Collectors.toList());
    }

}
