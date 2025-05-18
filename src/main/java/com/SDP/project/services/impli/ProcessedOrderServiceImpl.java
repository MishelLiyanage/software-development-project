package com.SDP.project.services.impli;

import com.SDP.project.DTOs.ProcessedOrderDTO;
import com.SDP.project.DTOs.ProcessedOrderRequest;
import com.SDP.project.DTOs.ProcessedOrderSchoolDTO;
import com.SDP.project.Repository.OrderRepository;
import com.SDP.project.Repository.PaperNumberingRepository;
import com.SDP.project.Repository.ProcessedOrderRepository;
import com.SDP.project.Repository.SchoolRepository;
import com.SDP.project.models.*;
import com.SDP.project.services.ProcessedOrderService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProcessedOrderServiceImpl implements ProcessedOrderService {

    @Autowired
    private ProcessedOrderRepository processedOrderRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private SchoolRepository schoolRepository;
    @Autowired
    private PaperNumberingRepository paperNumberingRepository;

    @Transactional
    public void saveProcessedOrder(ProcessedOrderRequest request) {
        ProcessedOrderDTO orderDTO = request.getOrder();
        ProcessedOrderSchoolDTO schoolDTO = request.getSchool();

        Order order = orderRepository.findOrderById(orderDTO.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order not found with ID: " + orderDTO.getOrderId()));

        order.setOrderStatus("Processed");

        orderRepository.save(order);

        int schoolId = order.getSchoolId();

        // Fetch the existing school entity from the DB
        School school = schoolRepository.findById(schoolId)
                .orElseThrow(() -> new RuntimeException("School not found with ID: " + schoolId));

        // Update existing school entity fields
        school.setName(schoolDTO.getName());
        school.setAddress(schoolDTO.getAddress());
        school.setContactNo(schoolDTO.getContactNo());
        school.setEmail(schoolDTO.getEmail());
        school.setCity(schoolDTO.getCity());

        // Save the updated school
        schoolRepository.save(school);

        // Now create and save the processed order
        ProcessedOrder processedOrder = new ProcessedOrder();
        processedOrder.setOrderId(orderDTO.getOrderId());
        processedOrder.setProcessedQuantity(orderDTO.getProcessedQuantity());
        processedOrder.setSequenceNo(orderDTO.getSequenceNo());
        processedOrder.setDateProcessed(LocalDate.parse(orderDTO.getDateProcessed()));
        processedOrder.setTimeProcessed(LocalTime.parse(orderDTO.getTimeProcessed()));
        processedOrder.setFromPaperNo(orderDTO.getFromPaperNo());
        processedOrder.setToPaperNo(orderDTO.getToPaperNo());
        processedOrder.setSchool(school);  // updated school

        List<ProcessedOrderCounter> counters = new ArrayList<>();
        for (int i = 0; i < 16; i++) {
            ProcessedOrderCounter counter = new ProcessedOrderCounter();
            counter.setPaperNo(i + 1);
            counter.setCounterFrom(orderDTO.getCounterFromNumbers().get(i));
            counter.setCounterTo(orderDTO.getCounterToNumbers().get(i));
            counter.setProcessedOrder(processedOrder);
            counters.add(counter);
        }

        processedOrder.setCounters(counters);

        PaperNumbering existing = paperNumberingRepository.getPaperNumberingByGradeAndCategory(request.getOrder().getGrade(), request.getOrder().getCategory());

        existing.setCounterNumber(request.getOrder().getLastCounterNumber());
        existing.setGrade(request.getOrder().getGrade());
        existing.setCategory(request.getOrder().getCategory());

        paperNumberingRepository.save(existing);

        // Save the processed order (cascade saves counters)
        processedOrderRepository.save(processedOrder);
    }
}
