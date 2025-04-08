package com.SDP.project.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskEmployeeAssignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int taskId;

    @Column(name = "assigned_date")
    private LocalDate assignedDate;

    private LocalDate deadline;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee assignedEmployee;
}