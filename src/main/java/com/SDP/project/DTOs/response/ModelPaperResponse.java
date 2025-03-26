package com.SDP.project.DTOs.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ModelPaperResponse {
    private boolean success;
    private int modelPaperId;
}
