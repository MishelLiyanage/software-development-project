package com.SDP.project.services.impli;

import com.SDP.project.DTOs.MonthlyRevenue;
import com.SDP.project.DTOs.PaymentRequestDto;
import com.SDP.project.DTOs.PaymentRevenueDto;
import com.SDP.project.Repository.OrderRepository;
import com.SDP.project.Repository.PaymentRepository;
import com.SDP.project.models.Order;
import com.SDP.project.models.Payment;
import com.SDP.project.services.PaymentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Payment savePaymentDetails(@Valid PaymentRequestDto paymentRequestDto) {
        // In your service method:
        Order order = orderRepository.findById(paymentRequestDto.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order not found with ID: " + paymentRequestDto.getOrderId()));

        Payment payment = new Payment();

        payment.setSchoolId(paymentRequestDto.getSchoolId());
        payment.setOrderId(paymentRequestDto.getOrderId());
        payment.setAmount(paymentRequestDto.getAmount());

        if (Objects.equals(paymentRequestDto.getPaymentMethod(), "Online")) {
            payment.setStatus("Paid");
        } else if (Objects.equals(paymentRequestDto.getPaymentMethod(), "Cash")) {
            payment.setStatus("Pending");
        }

        payment.setDate(new Date());
        payment.setTime(LocalTime.now());
        payment.setPaymentMethod(paymentRequestDto.getPaymentMethod());

        return paymentRepository.save(payment);
    }

    @Override
    public List<MonthlyRevenue> getMonthlyRevenue() {
        List<Object[]> results = paymentRepository.findMonthlyRevenue();
        List<MonthlyRevenue> revenueList = new ArrayList<>();

        for (Object[] row : results) {
            String month = (String) row[0];
            double amount = ((Number) row[1]).doubleValue();
            revenueList.add(new MonthlyRevenue(month, amount));
        }

        return revenueList;
    }

    @Override
    public List<PaymentRevenueDto> getRevenueByMethod() {
        List<Object[]> results = paymentRepository.getRevenueByPaymentMethod();
        List<PaymentRevenueDto> revenueList = new ArrayList<>();

        for (Object[] row : results) {
            String method = (String) row[0];
            double amount = ((Number) row[1]).doubleValue();
            revenueList.add(new PaymentRevenueDto(method, amount));
        }

        return revenueList;
    }
}
