package com.SDP.project.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @OneToMany(mappedBy = "department", fetch = FetchType.EAGER) // 🔹 Change from LAZY to EAGER
    private List<Employee> employees;

//    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY) // Set to LAZY to avoid fetching all paper types at once
//    private List<PaperTypes> paperTypes;

    public Department(String name) {
        this.name = name;
    }
}
