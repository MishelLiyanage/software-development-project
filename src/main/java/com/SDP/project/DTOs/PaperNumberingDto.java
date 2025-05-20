package com.SDP.project.DTOs;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaperNumberingDto {
    private int CounterNumber;
    private String grade;
    private String category;
    private String sequenceNo;
}
