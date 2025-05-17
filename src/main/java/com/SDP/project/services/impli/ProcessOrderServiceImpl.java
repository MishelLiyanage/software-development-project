package com.SDP.project.services.impli;

import com.SDP.project.DTOs.ProcessOrderDetailsDto;
import com.SDP.project.Repository.OrderRepository;
import com.SDP.project.Repository.PapersetRepository;
import com.SDP.project.Repository.PaymentRepository;
import com.SDP.project.Repository.SchoolRepository;
import com.SDP.project.models.*;
import com.SDP.project.services.ProcessOrderService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class ProcessOrderServiceImpl implements ProcessOrderService {
    @Autowired
    private  OrderRepository orderRepository;
    @Autowired
    private SchoolRepository schoolRepository;
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private PapersetRepository papersetRepository;

    @Override
    public List<String> getScholarshipTamilPendingOrderIds() {
        return orderRepository.findPendingOrderIdsWithScholarshipTamil();
    }

    @Override
    @Transactional
    public ResponseEntity<ProcessOrderDetailsDto> getOrderDetails(String orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow();
        Optional<Payment> payment = Optional.of(paymentRepository.findByOrderId(orderId).orElseThrow());
        int quantity = order.getOrderItems()
                .stream()
                .filter(item -> {
                    int paperSetId = item.getPaperSetId();
                    PaperSets paperSet = papersetRepository.findById(paperSetId).orElseThrow();
                    return "Grade 5".equals(paperSet.getGrade()) && "Scholarship Tamil".equals(paperSet.getCategory());
                })
                .mapToInt(OrderItem::getQuantity)
                .sum();

        School school = schoolRepository.findById(order.getSchoolId()).orElseThrow();

        ProcessOrderDetailsDto dto = new ProcessOrderDetailsDto(
                order.getDate(),
                payment.get().getAmount(),
                quantity,
                school.getName(),
                school.getAddress(),
                school.getContactNo(),
                school.getEmail(),
                school.getCity()
        );

        return ResponseEntity.ok(dto);
    }

}
