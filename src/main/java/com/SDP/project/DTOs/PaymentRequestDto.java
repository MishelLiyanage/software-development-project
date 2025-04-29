package com.SDP.project.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequestDto {
    @JsonProperty("school_id")
    private int schoolId;

    @JsonProperty("order_id")
    private int orderId;

    private float amount;

    @JsonProperty("payment_method")
    private String paymentMethod;
}
