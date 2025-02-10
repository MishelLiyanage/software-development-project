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

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String address;

    @Column(name = "contact_no")
    private String contactNo;

    private String status;

    @Column(name = "profile_pic")
    private String profilePic;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    private Department department;

    public Employee(String firstName, String lastName, String address, String contactNo, String status, String profilePic) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.contactNo = contactNo;
        this.status = status;
        this.profilePic = profilePic;
    }
}
