package com.SDP.project.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class School {
    @Id
 //   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String address;

    @Column(name = "contact_no")
    private String contactNo;

    private String email;

    @Column(name = "principle_name")
    private String principleName;

    @Column(name = "principle_signature")
    private String principleSignature;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;


    public School(String name, String address, String contactNo, String email, String principleName, String principleSignature) {
        this.name = name;
        this.address = address;
        this.contactNo = contactNo;
        this.email = email;
        this.principleName = principleName;
        this.principleSignature = principleSignature;
    }
}
