package com.SDP.project.services.impli;

import com.SDP.project.DTOs.*;
import com.SDP.project.DTOs.response.OrderResponseDto;
import com.SDP.project.DTOs.response.SchoolOrderResponseDto;
import com.SDP.project.Repository.*;
import com.SDP.project.models.*;
import com.SDP.project.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    @Value("${payhere.merchant-id}")
    private String merchant_id;

    @Value("${payhere.merchant-secret}")
    private String merchant_secret;

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private ModelPaperRepository modelPaperRepository;
    @Autowired
    private PapersetRepository papersetRepository;

    @Override
    @Transactional
    public ResponseEntity<OrderResponseDto> placeOrder(OrderRequestDTO orderRequestDTO) {
        Order order = new Order();
        order.setSchoolId(orderRequestDTO.getSchoolId());
        order.setStatus(orderRequestDTO.getStatus() != null ? orderRequestDTO.getStatus() : "Order Completed");
        order.setNotes(orderRequestDTO.getNotes());
        order.setOrderStatus("Pending");
        order.setDate(LocalDate.now());
        order.setTime(LocalTime.now());

        // Generate custom order ID
        String customOrderId = generateCustomOrderId(orderRequestDTO.getPaymentMethod());
        order.setId(customOrderId);

        List<OrderItem> orderedItems = orderRequestDTO.getOrderedPublications();

        for (OrderItem item : orderedItems) {
            item.setOrder(order);
            item.setOrderStatus("Pending");
        }
        order.setOrderItems(orderedItems);

        Order savedOrder = orderRepository.save(order);

        // generate the hash value for the payment
        String merchantSecret = merchant_secret;
        String orderId = savedOrder.getId(); // Now a String like "LO-0001"
        String amount = String.format("%.2f", orderRequestDTO.getTotalAmount());
        String currency = "LKR"; // assuming Sri Lankan Rupees

        String rawHashString = merchant_id + orderId + amount + currency + md5(merchantSecret);
        String hash = md5(rawHashString).toUpperCase();

        OrderResponseDto responseDto = new OrderResponseDto(
                true,
                savedOrder.getId(),
                savedOrder.getStatus(),
                merchant_id,
                hash
        );

        return ResponseEntity.ok(responseDto);
    }

    private String generateCustomOrderId(String paymentMethod) {
        // Find the latest order to determine the next number
        Order latestOrder = orderRepository.findTopByOrderByIdDesc();
        int nextNumber = 1;

        if (latestOrder != null && latestOrder.getId() != null) {
            String latestId = latestOrder.getId();
            // Extract numeric part by removing prefix (LO- or VP-)
            String numericPart = latestId.replaceFirst("^(LO-|VP-)", "");
            try {
                nextNumber = Integer.parseInt(numericPart) + 1;
            } catch (NumberFormatException e) {
                // Fallback to 1 if the numeric part is invalid
                nextNumber = 1;
            }
        }

        // Format the number as a 4-digit string (e.g., 1 -> "0001")
        DecimalFormat formatter = new DecimalFormat("0000");
        String formattedNumber = formatter.format(nextNumber);

        // Determine prefix based on payment method
        if ("Online".equals(paymentMethod) || "Bank Payment / Any Other".equals(paymentMethod)) {
            return "LO-" + formattedNumber;
        } else if ("Cash".equals(paymentMethod)) {
            return "VP-" + formattedNumber;
        } else {
            // Fallback for invalid or unrecognized payment methods
            return "LO-" + formattedNumber; // Default to LO- prefix
        }
    }

    private String md5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext.toUpperCase();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<AllOrdersDto> getAllOrders() {
        return orderRepository.findAllOrdersWithDetails().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private AllOrdersDto convertToDTO(Object[] result) {
        AllOrdersDto dto = new AllOrdersDto();
        dto.setOrderId((String) result[0]);
        dto.setSchoolName((String) result[1]);
        dto.setCity((String) result[2]);
        dto.setPaymentStatus(result[3] != null ? (String) result[3] : "");
        dto.setPaymentMethod(result[4] != null ? (String) result[4] : "");
        dto.setOrderStatus(result[5] != null ? (String) result[5] : "");

        // Parse date (expected format: "yyyy-MM-dd")
        if (result[6] instanceof java.sql.Date) {
            dto.setDate(new java.util.Date(((java.sql.Date) result[6]).getTime()));
        } else {
            dto.setDate(null);
        }

        // result[6] is java.sql.Time or java.time.LocalTime
        if (result[7] instanceof java.sql.Time) {
            dto.setTime(((java.sql.Time) result[7]).toLocalTime());
        } else if (result[7] instanceof LocalTime) {
            dto.setTime((LocalTime) result[7]);
        } else {
            dto.setTime(null);
        }

        dto.setAmount(result[8] != null ? result[8].toString() : "");
        return dto;
    }

    @Override
    public UpdateOrderDto updateOrder(UpdateOrderDto updatedOrder) {
        Optional<Payment> existingOrderOpt = paymentRepository.findByOrderId(updatedOrder.getOrderId());

        if (existingOrderOpt.isPresent()) {
            Payment existingOrder = existingOrderOpt.get();

            existingOrder.setStatus(updatedOrder.getPaymentStatus());
            existingOrder.setPaymentMethod(updatedOrder.getPaymentMethod());

            Payment payment = paymentRepository.save(existingOrder);

            UpdateOrderDto updatedOrderDto = new UpdateOrderDto();

            updatedOrderDto.setOrderId(payment.getOrderId());
            updatedOrderDto.setPaymentStatus(payment.getStatus());
            updatedOrderDto.setPaymentMethod(payment.getPaymentMethod());

            return updatedOrderDto;
        } else {
            throw new RuntimeException("Order with ID " + updatedOrder.getOrderId() + " not found.");
        }
    }

    @Override
    @Transactional
    public void deleteOrder(String orderId) {
//        Order order = orderRepository.findOrderById(orderId)
//                .orElseThrow(() -> new RuntimeException("Order not found with ID: " + orderId));
//        orderRepository.delete(order);
//
//        List<OrderItem> orderItems = orderItemRepository.getOrderItemsByOrderId(orderId);
//
//        if (!orderItems.isEmpty()) {
//            orderItemRepository.deleteAll(orderItems);
//            System.out.println("Deleted " + orderItems.size() + " order items for orderId: " + orderId);
//        } else {
//            System.out.println("No order items found for orderId: " + orderId);
//        }
//
//        Payment payment = paymentRepository.findByOrderId(orderId)
//                .orElseThrow(() -> new RuntimeException("Payment not found with ID: " + orderId));
//        paymentRepository.delete(payment);

    }

    @Override
    public List<OrderCategoryData> getOrderDistribution() {
        List<Object[]> rawResults = orderItemRepository.getRawOrderDistribution("Scholarship");

        long total = rawResults.stream()
                .mapToLong(obj -> (Long) obj[1])
                .sum();

        return rawResults.stream()
                .map(obj -> new OrderCategoryData(
                        (String) obj[0],
                        Math.round(((Long) obj[1]) * 100.0 / total * 100.0) / 100.0 // round to 2 decimals
                ))
                .collect(Collectors.toList());
    }

    public List<MonthlyOrdersDataDto> getMonthlyProcessedOrders() {
        List<Object[]> results = orderRepository.findMonthlyOrders();
        List<MonthlyOrdersDataDto> dataList = new ArrayList<>();

        for (Object[] row : results) {
            String month = (String) row[0];
            int count = ((Number) row[1]).intValue();
            dataList.add(new MonthlyOrdersDataDto(month, count));
        }

        return dataList;
    }

    @Override
    @Transactional
    public List<SchoolOrderResponseDto> getOrdersBySchool(int schoolId) {
        List<Order> orders = orderRepository.findAllBySchoolId(schoolId);

        // Reverse the order list to get the most recent orders first
        Collections.reverse(orders);

        return orders.stream().map(order -> {
            List<String> itemDescriptions = new ArrayList<>();

            for (OrderItem item : order.getOrderItems()) {
                PaperSets paper = papersetRepository.findById(item.getPaperSetId()).orElse(null);
                if (paper != null) {
                    String name = paper.getGrade() + " " +
                            paper.getCategory() +
                            " x " + item.getQuantity();
                    itemDescriptions.add(name);
                }
            }

            Payment payment = paymentRepository.findByOrderId(order.getId()).orElse(null);
            String paymentStatus = (payment != null) ? payment.getStatus() : "Not Paid";
            double amount = (payment != null) ? payment.getAmount() : 0.0;

            return new SchoolOrderResponseDto(
                    order.getId(),
                    itemDescriptions,
                    order.getDate().toString(),
                    paymentStatus,
                    order.getOrderStatus(),
                    amount
            );
        }).collect(Collectors.toList());
    }
}