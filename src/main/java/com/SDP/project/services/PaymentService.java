package com.SDP.project.services;

import com.SDP.project.DTOs.MonthlyRevenue;
import com.SDP.project.DTOs.PaymentRequestDto;
import com.SDP.project.DTOs.PaymentRevenueDto;
import com.SDP.project.models.Payment;
import jakarta.validation.Valid;

import java.util.List;

public interface PaymentService {
    Payment savePaymentDetails(@Valid PaymentRequestDto paymentRequestDto);
    List<MonthlyRevenue> getMonthlyRevenue();
    List<PaymentRevenueDto> getRevenueByMethod();
}
