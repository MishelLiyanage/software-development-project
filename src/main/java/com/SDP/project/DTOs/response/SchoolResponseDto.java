package com.SDP.project.DTOs.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SchoolResponseDto {
    private int id;
    private String username;
    private String password;
    private String role;
    private String name;
    private String address;
    private String contactNo;
    private String email;
    private String principleName;
    private String principleSignature;

    public SchoolResponseDto(int id, String username, String password, String role, String name, String address, String contactNo, String email, String principleName, String principleSignature) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.name = name;
        this.address = address;
        this.contactNo = contactNo;
        this.email = email;
        this.principleName = principleName;
        this.principleSignature = principleSignature;
    }

    public SchoolResponseDto(String name, String address, String email, String contactNo, String username, String principleName) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.contactNo = contactNo;
        this.username = username;
        this.principleName = principleName;
    }
}
