package com.SDP.project.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;
import java.util.Date;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "school_id")
    private int schoolId;

    @Column(name = "order_id")
    private int orderId;

    private float amount;

    private String status;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date date;

    @Column(nullable = false)
    private LocalTime time;

    @Column(name = "payment_method", nullable = false)
    private String paymentMethod;
}
