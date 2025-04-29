package com.SDP.project.DTOs;

import com.SDP.project.models.OrderItem;
import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestDTO {
    private int schoolId;
    private String status;
    private List<OrderItem> orderedPublications;
    private float totalAmount;
    private String notes; // if needed

}