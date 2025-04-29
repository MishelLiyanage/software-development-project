package com.SDP.project.services;

import com.SDP.project.DTOs.PaperSetDto;
import com.SDP.project.models.PaperSets;
import jakarta.validation.Valid;

public interface PaperSetService {
    PaperSets savePaperType(@Valid PaperSetDto paperSetDto);

    double getPriceByName(String publicationName);

    Integer getPaperSetIdByPublicationName(String publicationName);
}
