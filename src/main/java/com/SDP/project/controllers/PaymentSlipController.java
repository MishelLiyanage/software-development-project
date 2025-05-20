package com.SDP.project.controllers;

import com.SDP.project.Repository.PaymentRepository;
import com.SDP.project.models.Payment;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalTime;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/paymentSlip")
@PreAuthorize("hasAnyRole('ROLE_SCHOOL')")
public class PaymentSlipController {

    @Autowired
    private Cloudinary cloudinary;
    @Autowired
    private PaymentRepository paymentRepository;

    @PostMapping("/upload-slip")
    public ResponseEntity<Map<String, Object>> uploadSlip(@RequestParam("file") MultipartFile file,
                                                          @RequestParam("userId") int userId,
                                                          @RequestParam("orderId") String orderId,
                                                          @RequestParam("amount") float amount,
                                                          @RequestParam("paymentMethod") String paymentMethod) {
        try {
            Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            String url = (String) uploadResult.get("secure_url");

            Payment payment = new Payment();
            payment.setSchoolId(userId);
            payment.setOrderId(orderId);
            payment.setAmount(amount);
            payment.setStatus("Paid");
            payment.setDate(new Date());
            payment.setTime(LocalTime.now());
            payment.setPaymentMethod(paymentMethod);
            payment.setBankSlipUrl(url);
            paymentRepository.save(payment);

            return ResponseEntity.ok(Map.of(
                    "success", true,
                    "message", "Upload and save successful",
                    "url", url,
                    "paymentId", payment.getId()
            ));
        } catch (IOException e) {
            return ResponseEntity.status(500).body(Map.of(
                    "success", false,
                    "message", "Upload failed",
                    "error", e.getMessage()
            ));
        }
    }
}

