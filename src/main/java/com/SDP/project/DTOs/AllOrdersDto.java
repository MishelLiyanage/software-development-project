package com.SDP.project.DTOs;

import lombok.*;

import java.time.LocalTime;
import java.util.Date;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AllOrdersDto {
    private String orderId;
    private String schoolName;
    private String city;
    private String paymentStatus;
    private String paymentMethod;
    private String orderStatus;
    private Date date;
    private LocalTime time;
    private String amount;
}