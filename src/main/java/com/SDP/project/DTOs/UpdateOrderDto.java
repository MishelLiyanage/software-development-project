package com.SDP.project.DTOs;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateOrderDto {
    private String orderId;
    private String paymentStatus;
    private String paymentMethod;
}
