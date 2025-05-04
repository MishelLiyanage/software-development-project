package com.SDP.project.DTOs.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OrderResponseDto {
    private boolean success;

    @JsonProperty("order_id")
    private String orderId;

    private String status;

    @JsonProperty("merchant_id")
    private String merchantId;

    private String hashValue;
}
