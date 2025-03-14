package com.SDP.project.DTOs.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeResponseDto {
    private int id;
    private String username;
    private String password;
    private String role;
    private String firstName;
    private String lastName;
    private String address;
    private String contactNo;
    private String status;
    private String profilePic;

    public EmployeeResponseDto(int id, String username, String password, String role, String firstName, String lastName, String address, String contactNo, String status, String profilePic) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.contactNo = contactNo;
        this.status = status;
        this.profilePic = profilePic;
    }
}
