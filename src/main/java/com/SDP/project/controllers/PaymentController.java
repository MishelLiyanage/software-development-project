package com.SDP.project.controllers;

import com.SDP.project.DTOs.PaymentRequestDto;
import com.SDP.project.DTOs.response.PaymentResponse;
import com.SDP.project.models.Payment;
import com.SDP.project.services.PaymentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/payment")
@RestController
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @PostMapping("/")
    @PreAuthorize("hasAnyRole('ROLE_SCHOOL')")
    public PaymentResponse savePaymentDetails(@Valid @RequestBody PaymentRequestDto paymentRequestDto) {
        Payment payment = paymentService.savePaymentDetails(paymentRequestDto);
        return new PaymentResponse(true, payment);
    }
}