package com.SDP.project.DTOs;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdminStatDto {
    private int registeredSchools;
    private int totalOrders;
    private int grade5scholarship;
    private int grade4scholarship;
    private int grade3scholarship;
    private int ordersToProcess;
}
