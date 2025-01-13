package com.SDP.project.DTOs.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeResponseDto {
    private int id;
    private String username;
    private String role;
    private String first_name;
    private String last_name;
    private String address;
    private String contact_no;
    private String status;
    private String profile_pic;

    public EmployeeResponseDto(int id, String username, String role, String firstName, String lastName, String address, String contactNo, String status, String profilePic) {
        this.id = id;
        this.username = username;
        this.role = role;
        this.first_name = firstName;
        this.last_name = lastName;
        this.address = address;
        this.contact_no = contactNo;
        this.status = status;
        this.profile_pic = profilePic;
    }
}
