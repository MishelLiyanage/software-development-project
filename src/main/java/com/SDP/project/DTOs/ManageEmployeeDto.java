package com.SDP.project.DTOs;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ManageEmployeeDto {
    private String username;

    private String firstName;

    private String lastName;

    private String address;

    private String contactNo;

    private String departmentName;
}
