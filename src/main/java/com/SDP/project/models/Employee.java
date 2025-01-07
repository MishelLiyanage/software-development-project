package com.SDP.project.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    @Id
    private int id;

    private String first_name;
    private String last_name;
    private String address;
    private String contact_no;
    private String status;
    private String profile_pic;

    public Employee(String firstName, String lastName, String address, String contactNo, String status, String profilePic) {
        this.first_name = firstName;
        this.last_name = lastName;
        this.address = address;
        this.contact_no = contactNo;
        this.status = status;
        this.profile_pic = profilePic;
    }
}
