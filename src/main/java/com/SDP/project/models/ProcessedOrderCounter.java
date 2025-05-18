package com.SDP.project.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProcessedOrderCounter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int paperNo;
    private int counterFrom;
    private int counterTo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "processed_order_id")
    private ProcessedOrder processedOrder;
}
