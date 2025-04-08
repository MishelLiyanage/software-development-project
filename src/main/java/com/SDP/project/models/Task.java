package com.SDP.project.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import net.bytebuddy.asm.Advice;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

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

    @Column(name = "created_date")
    private LocalDate createdDate;

    @Column(name = "created_time")
    private LocalTime createdTime;

    private String status;
}
