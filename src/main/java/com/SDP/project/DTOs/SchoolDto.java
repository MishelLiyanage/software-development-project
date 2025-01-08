package com.SDP.project.DTOs;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SchoolDto {
    private String username;

    @NotBlank(message = "Password cannot be blank")
    private String password;
    private String role;
    private String name;
    private String address;
    private String contact_no;
    private String email;
    private String principle_name;
    private String principle_signature;
}
