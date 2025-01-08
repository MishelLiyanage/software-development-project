package com.SDP.project.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class School {
    @Id
 //   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String address;
    private String contact_no;
    private String email;
    private String principle_name;
    private String principle_signature;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;


    public School(String name, String address, String contactNo, String email, String principleName, String principleSignature) {
        this.name = name;
        this.address = address;
        this.contact_no = contactNo;
        this.email = email;
        this.principle_name = principleName;
        this.principle_signature = principleSignature;
    }
}
