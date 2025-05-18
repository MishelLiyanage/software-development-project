package com.SDP.project.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProcessedOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String orderId;
    private int processedQuantity;
    private String sequenceNo;
    private LocalDate dateProcessed;
    private LocalTime timeProcessed;
    private int fromPaperNo;
    private int toPaperNo;

    @OneToMany(mappedBy = "processedOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProcessedOrderCounter> counters = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "school_id", referencedColumnName = "id")
    private School school;
}
