package com.SDP.project.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PrintingProgress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int taskId;

    @Column(name = "submitted_date")
    private LocalDate submittedDate;

    private LocalDate deadline;

    @Column(name = "expected_quantity")
    private int expectedQuantity;

    @Column(name = "remaining_to_print_quantity")
    private int remainingToPrintQuantity;

    @Column(name = "is_started")
    private boolean isStarted;

    @Column(name = "is_sent_to_inventory")
    private boolean isSentToInventory;
}
