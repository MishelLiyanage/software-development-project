package com.SDP.project.DTOs.response;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRegistrationResponseDto {
    private boolean success;
    private String message;
}
