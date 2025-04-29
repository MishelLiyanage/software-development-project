package com.SDP.project.DTOs.response;

import com.SDP.project.models.Order;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

@Getter
@Setter
@AllArgsConstructor
public class OrderResponseDto {
    private boolean success;

    @JsonProperty("order_id")
    private int orderId;

    private String status;

    @JsonProperty("merchant_id")
    private String merchantId;

    private String hashValue;
}
