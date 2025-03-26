package com.SDP.project.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int taskId;

    @ManyToOne
    @JoinColumn(name = "modelPaperId", nullable = false)
    private ModelPaper modelPaper;

    @Column(name = "created_Date")
    private LocalDate createdDate;

    @Column(name = "created_Time")
    private LocalTime createdTime;

    private String status;
}
