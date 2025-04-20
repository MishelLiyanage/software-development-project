package com.SDP.project.DTOs.response;

import com.SDP.project.models.PaperSets;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PaperSetResponse {
    private boolean success;
    private PaperSets paperSets;
}
