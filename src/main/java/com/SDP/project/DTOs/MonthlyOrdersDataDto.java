package com.SDP.project.DTOs;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MonthlyOrdersDataDto {
    private String month;
    private int orders;
}
