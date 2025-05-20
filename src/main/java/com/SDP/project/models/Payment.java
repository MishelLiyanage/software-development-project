package com.SDP.project.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
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
    private String orderId;

    private float amount;

    private String status;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date date;

    @Column(nullable = false)
    private LocalTime time;

    @Column(name = "payment_method", nullable = false)
    private String paymentMethod;

    @Column(name = "bank_slip_url")
    private String bankSlipUrl;

    public Payment(int userId, String orderId, float amount, String status, Date date, LocalTime time, String paymentMethod, String url) {
        this.orderId = orderId;
        this.schoolId = userId;
        this.amount = amount;
        this.status = status;
        this.date = date;
        this.time = time;
        this.paymentMethod = paymentMethod;
        this.bankSlipUrl = url;
    }
}
