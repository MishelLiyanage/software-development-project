package com.SDP.project.services;

import com.SDP.project.DTOs.PaperNumberingDto;
import com.SDP.project.DTOs.response.NumberingCounterDetails;
import com.SDP.project.models.PaperNumbering;

public interface PaperNumberingService {
    PaperNumbering saveInitialCounter(PaperNumberingDto paperNumberingDto);
    NumberingCounterDetails getCounterNumber(String grade, String category);
}
