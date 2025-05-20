package com.SDP.project.services.impli;

import com.SDP.project.DTOs.ProcessedOrderDTO;
import com.SDP.project.DTOs.ProcessedOrderRequest;
import com.SDP.project.DTOs.ProcessedOrderSchoolDTO;
import com.SDP.project.Repository.*;
import com.SDP.project.models.*;
import com.SDP.project.services.ProcessedOrderService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private PapersetRepository paperSetRepository;
    @Autowired
    private EmailServiceImpl emailService;
    @Autowired
    private InventoryRepository inventoryRepository;
    @Autowired
    private ModelPaperRepository modelPaperRepository;

    @Transactional
    public void saveProcessedOrder(ProcessedOrderRequest request) {
        try {
            ProcessedOrderDTO orderDTO = request.getOrder();
            ProcessedOrderSchoolDTO schoolDTO = request.getSchool();

            PaperSets paperSet = paperSetRepository.findByGradeAndCategory(orderDTO.getGrade(), orderDTO.getCategory());
            if (paperSet == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Paper set not found for given grade and category");
            }

            OrderItem orderItem = orderItemRepository.findByOrderIdAndPaperSetId(orderDTO.getOrderId(), paperSet.getId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order item not found for the given order ID"));

            orderItem.setOrderStatus("Processed");
            orderItemRepository.save(orderItem);

            Order order = orderRepository.findOrderById(orderDTO.getOrderId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found with ID: " + orderDTO.getOrderId()));

            int schoolId = order.getSchoolId();

            School school = schoolRepository.findById(schoolId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "School not found with ID: " + schoolId));

            school.setName(schoolDTO.getName());
            school.setAddress(schoolDTO.getAddress());
            school.setContactNo(schoolDTO.getContactNo());
            school.setEmail(schoolDTO.getEmail());
            school.setCity(schoolDTO.getCity());
            schoolRepository.save(school);

            ProcessedOrder processedOrder = new ProcessedOrder();
            processedOrder.setOrderId(orderDTO.getOrderId());
            processedOrder.setProcessedQuantity(orderDTO.getProcessedQuantity());
            processedOrder.setSequenceNo(orderDTO.getSequenceNo());
            processedOrder.setDateProcessed(LocalDate.parse(orderDTO.getDateProcessed()));
            processedOrder.setTimeProcessed(LocalTime.parse(orderDTO.getTimeProcessed()));
            processedOrder.setFromPaperNo(orderDTO.getFromPaperNo());
            processedOrder.setToPaperNo(orderDTO.getToPaperNo());
            processedOrder.setSchool(school);

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

            // Inventory update based on paper number and part number
            int from = processedOrder.getFromPaperNo();
            int to = processedOrder.getToPaperNo();

            for (int paperNum = from; paperNum <= to; paperNum++) {
                for (int partNum = 1; partNum <= 2; partNum++) {
                    String paperNo = "Paper " + paperNum;
                    String partNo = "Part " + partNum;

                    ModelPaper modelPaper = modelPaperRepository.findByGradeAndCategoryAndPaperNoAndPartNo(
                            orderDTO.getGrade(), orderDTO.getCategory(), paperNo, partNo
                    ).orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.NOT_FOUND, "Model paper not found for " + paperNo + " - " + partNo));

                    Inventory inventory = inventoryRepository.findByModelPaperId(modelPaper.getId())
                            .orElseThrow(() -> new ResponseStatusException(
                                    HttpStatus.NOT_FOUND, "Inventory not found for ModelPaper ID: " + modelPaper.getId()));

                    int updatedQuantity = inventory.getQuantity() - orderDTO.getProcessedQuantity();
                    if (updatedQuantity < 0) {
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                                "Inventory for ModelPaper ID " + modelPaper.getId() + " would go negative.");
                    }

                    inventory.setQuantity(updatedQuantity);
                    inventoryRepository.save(inventory);
                }
            }

            PaperNumbering existing = paperNumberingRepository.getPaperNumberingByGradeAndCategory(orderDTO.getGrade(), orderDTO.getCategory());
            existing.setCounterNumber(orderDTO.getLastCounterNumber());
            existing.setGrade(orderDTO.getGrade());
            existing.setCategory(orderDTO.getCategory());
            paperNumberingRepository.save(existing);

            processedOrderRepository.save(processedOrder);

            try {
                emailService.sendOrderProcessedNotification(processedOrder, school, order, paperSet);
            } catch (Exception e) {
                System.err.println("Failed to send email: " + e.getMessage());
            }

        } catch (ResponseStatusException e) {
            throw e; // re-throw to be handled by Spring
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error occurred: " + e.getMessage());
        }
    }
}
