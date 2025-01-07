package com.SDP.project.DTOs;

import lombok.Data;

@Data
public class EmployeeDto {
    private String username;
    private String password;
    private String role;
    private String first_name;
    private String last_name;
    private String address;
    private String contact_no;
    private String status;
    private String profile_pic;
}
