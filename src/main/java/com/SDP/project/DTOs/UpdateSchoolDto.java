package com.SDP.project.DTOs;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateSchoolDto {
    private int schoolId;
    private String name;
    private String address;
    private String city;
    private String email;
    private String contactNo;
    private String principleName;
}
