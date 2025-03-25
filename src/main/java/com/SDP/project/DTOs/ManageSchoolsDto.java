package com.SDP.project.DTOs;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ManageSchoolsDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int schoolId;

    @Column(name = "account_id")
    private int accountId;

    private String name;
    private String address;
    private String email;
    private String contactNo;
    private String username;
    private String principleName;

    public ManageSchoolsDto(int id, String name, String address, String email, String contactNo,  String username, String principleName) {
        this.schoolId = id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.contactNo = contactNo;
        this.username = username;
        this.principleName = principleName;
    }
}
