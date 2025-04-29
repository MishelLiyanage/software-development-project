package com.SDP.project.services.impli;

import com.SDP.project.DTOs.PaymentRequestDto;
import com.SDP.project.Repository.PaymentRepository;
import com.SDP.project.models.Payment;
import com.SDP.project.services.PaymentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.Date;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    public Payment savePaymentDetails(@Valid PaymentRequestDto paymentRequestDto) {
        Payment payment = new Payment();

        payment.setSchoolId(paymentRequestDto.getSchoolId());
        payment.setOrderId(paymentRequestDto.getOrderId());
        payment.setAmount(paymentRequestDto.getAmount());
        payment.setStatus("Paid");
        payment.setDate(new Date());
        payment.setTime(LocalTime.now());
        payment.setPaymentMethod(paymentRequestDto.getPaymentMethod());

        return paymentRepository.save(payment);
    }
}
