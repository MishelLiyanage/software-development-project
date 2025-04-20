package com.SDP.project.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

    @GetMapping("/success")
    public String paymentSuccess() {
        return "Payment successful!";
    }

    @GetMapping("/cancel")
    public String paymentCancel() {
        return "Payment canceled!";
    }
}