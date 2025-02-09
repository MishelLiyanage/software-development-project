package com.SDP.project.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
public class ModelPaper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String grade;
    private String category;

    @Column(name = "paper_no")
    private String paperNo;

    @Column(name = "part_no")
    private String partNo;

    private String description;

    public ModelPaper(String category, String grade, String paperNo, String partNo, String description) {
        this.category = category;
        this.grade = grade;
        this.paperNo = paperNo;
        this.partNo = partNo;
        this.description = description;
    }
}
