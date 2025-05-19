package com.SDP.project.services;

import com.SDP.project.DTOs.OrderDetailDto;

import java.util.List;

public interface ParcelOrderListService {
    List<OrderDetailDto> getOrderDetailsByIds(List<String> orderIds);
}
