package com.SDP.project.DTOs;

import lombok.*;

import java.time.LocalDate;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProcessOrderDetailsDto {
    private LocalDate dateOrdered;
    private double totalAmount;
    private int numberOfStudents;
    private String name;
    private String address;
    private String contactNo;
    private String email;
    private String city;
}
