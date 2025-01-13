package com.SDP.project.DTOs.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SchoolResponseDto {
    private int id;
    private String username;
    private String role;
    private String name;
    private String address;
    private String contact_no;
    private String email;
    private String principle_name;
    private String principle_signature;

    public SchoolResponseDto(int id, String username, String role, String name, String address, String contactNo, String email, String principleName, String principleSignature) {
        this.id = id;
        this.username = username;
        this.role = role;
        this.name = name;
        this.address = address;
        this.contact_no = contactNo;
        this.email = email;
        this.principle_name = principleName;
        this.principle_signature = principleSignature;
    }
}
