package com.SDP.project.DTOs.response;

import com.SDP.project.models.Payment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
public class PaymentResponse {
    private boolean success;
    private Payment payment;
}
