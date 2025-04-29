package com.SDP.project.services.impli;

import com.SDP.project.DTOs.PaperSetDto;
import com.SDP.project.Repository.PaperSetPriceRepository;
import com.SDP.project.Repository.PapersetRepository;
import com.SDP.project.models.PaperSetPrice;
import com.SDP.project.models.PaperSets;
import com.SDP.project.services.PaperSetService;
import com.SDP.project.shared.exceptions.RecordAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaperSetServiceImpl implements PaperSetService {
    @Autowired
    private PapersetRepository papersetRepository;

    @Autowired
    private PaperSetPriceRepository paperSetPriceRepository;

    @Override
    public PaperSets savePaperType(PaperSetDto paperSetDto) {
        isPapersetExist(paperSetDto);

        PaperSets paperSets = new PaperSets();
        paperSets.setGrade(paperSetDto.getGrade());
        paperSets.setCategory(paperSetDto.getCategory());

        return papersetRepository.save(paperSets);
    }

    private void isPapersetExist(PaperSetDto paperSetDto) {
        boolean isPaperSetExist = papersetRepository.existsModelPaper(
                paperSetDto.getGrade(),
                paperSetDto.getCategory()
        );

        System.out.println("Model Paper Exists: " + isPaperSetExist); // Debugging log

        if (isPaperSetExist) {
            throw new RecordAlreadyExistException("Model paper already exists.");
        }
    }

    @Override
    public double getPriceByName(String publicationName) {
        // Split publicationName into grade and category
        String[] tokens = publicationName.trim().split(" ");
        if (tokens.length < 3) {
            throw new IllegalArgumentException("Invalid publication name format.");
        }

        String grade = tokens[0] + " " + tokens[1]; // "Grade 5"
        StringBuilder categoryBuilder = new StringBuilder();
        for (int i = 2; i < tokens.length; i++) {
            categoryBuilder.append(tokens[i]);
            if (i != tokens.length - 1) {
                categoryBuilder.append(" ");
            }
        }
        String category = categoryBuilder.toString().trim();

        // Find the model paper by grade and category
        PaperSets Paperset = papersetRepository.findByGradeAndCategory(grade, category);
        if (Paperset == null) {
            throw new RuntimeException("Paper set not found for: " + publicationName);
        }

        // Now get the price by modelPaperId
        int modelPaperId = Paperset.getId();
        PaperSetPrice paperSetPrice = paperSetPriceRepository.findById(modelPaperId)
                .orElseThrow(() -> new RuntimeException("Price not found for modelPaperId: " + modelPaperId));

        return paperSetPrice.getPrice();
    }

    @Override
    public Integer getPaperSetIdByPublicationName(String publicationName) {
        String[] words = publicationName.split(" ", 3); // Split into 3 parts max

        String grade = words[0] + " " + words[1];  // Grade + Number
        String category = words[2];                // Remaining as category

        // Query the repository with grade and category
        int papersetId = papersetRepository.findPaperSetIdByPublicationName(grade, category);

        return papersetId;
    }
}
