package com.SDP.project.services;

import com.SDP.project.DTOs.PaymentRequestDto;
import com.SDP.project.models.Payment;
import jakarta.validation.Valid;

public interface PaymentService {
    Payment savePaymentDetails(@Valid PaymentRequestDto paymentRequestDto);
}
