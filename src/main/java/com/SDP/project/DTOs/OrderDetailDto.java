package com.SDP.project.DTOs;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailDto {
    private String orderId;
    private String principalName;
    private String schoolName;
    private String address;
    private String contactNo;
}
