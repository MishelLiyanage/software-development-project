package com.SDP.project.services.impli;

import com.SDP.project.DTOs.PaperNumberingDto;
import com.SDP.project.DTOs.response.NumberingCounterDetails;
import com.SDP.project.Repository.PaperNumberingRepository;
import com.SDP.project.models.PaperNumbering;
import com.SDP.project.services.PaperNumberingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaperNumberingServiceImpl implements PaperNumberingService {
    @Autowired
    private PaperNumberingRepository paperNumberingRepository;

    @Override
    public PaperNumbering saveInitialCounter(PaperNumberingDto paperNumberingDto) {
        boolean isExists = paperNumberingRepository.existsPaperNumberingByGradeAndCategory(
                paperNumberingDto.getGrade(), paperNumberingDto.getCategory()
        );

        PaperNumbering paperNumbering;

        if (isExists) {
            paperNumbering = paperNumberingRepository.getPaperNumberingByGradeAndCategory(
                    paperNumberingDto.getGrade(), paperNumberingDto.getCategory()
            );
            paperNumbering.setCounterNumber(paperNumberingDto.getCounterNumber());
        } else {
            paperNumbering = new PaperNumbering();
            paperNumbering.setGrade(paperNumberingDto.getGrade());
            paperNumbering.setCategory(paperNumberingDto.getCategory());
            paperNumbering.setCounterNumber(paperNumberingDto.getCounterNumber());
        }

        return paperNumberingRepository.save(paperNumbering); // This handles both insert and update
    }

    @Override
    public NumberingCounterDetails getCounterNumber(String grade, String category) {
        PaperNumbering numbering = paperNumberingRepository.findByGradeAndCategory(grade, category)
                .orElseThrow(() -> new RuntimeException("No paper numbering found for given grade and category"));

        return new NumberingCounterDetails(numbering.getCounterNumber() + 1);
    }
}
