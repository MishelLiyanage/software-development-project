package com.SDP.project.DTOs;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProcessedOrderSchoolDTO {
    private String name;
    private String address;
    private String contactNo;
    private String email;
    private String city;
}
