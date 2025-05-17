package com.SDP.project.DTOs.response;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaperProcessingDetailsResponse {
    private int fromPaperNo;
    private int toPaperNo;
    private int sequenceNo;
}
