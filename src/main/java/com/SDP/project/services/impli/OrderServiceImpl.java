package com.SDP.project.services.impli;

import com.SDP.project.DTOs.OrderRequestDTO;
import com.SDP.project.DTOs.response.OrderResponseDto;
import com.SDP.project.Repository.OrderRepository;
import com.SDP.project.Repository.PaymentRepository;
import com.SDP.project.models.Order;
import com.SDP.project.models.OrderItem;
import com.SDP.project.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

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

    @Override
    @Transactional
    public ResponseEntity<OrderResponseDto> placeOrder(OrderRequestDTO orderRequestDTO) {
        Order order = new Order();
        order.setSchoolId(orderRequestDTO.getSchoolId());
        order.setStatus(orderRequestDTO.getStatus() != null ? orderRequestDTO.getStatus() : "Order Completed");
        order.setNotes(orderRequestDTO.getNotes());

        List<OrderItem> orderedItems = orderRequestDTO.getOrderedPublications();

        for (OrderItem item : orderedItems) {
            item.setOrder(order); // set the parent order for each item
        }
        order.setOrderItems(orderedItems);

        Order savedOrder = orderRepository.save(order);

        // generate the hash value for the payment
        String merchantSecret = merchant_secret;
        String orderId = String.valueOf(savedOrder.getId());
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
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
