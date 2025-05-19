package com.SDP.project.services.impli;

import com.SDP.project.DTOs.OrderDetailDto;
import com.SDP.project.Repository.OrderRepository;
import com.SDP.project.Repository.SchoolRepository;
import com.SDP.project.models.Order;
import com.SDP.project.models.School;
import com.SDP.project.services.ParcelOrderListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ParcelOrderListServiceImpl implements ParcelOrderListService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private SchoolRepository schoolRepository;

    @Override
    public List<OrderDetailDto> getOrderDetailsByIds(List<String> orderIds) {
        List<OrderDetailDto> result = new ArrayList<>();

        for (String id : orderIds) {
            Order order = orderRepository.findById(id).orElse(null);
            if (order != null) {
                School school = schoolRepository.findById(order.getSchoolId()).orElse(null);
                if (school != null) {
                    result.add(new OrderDetailDto(
                            order.getId(),
                            school.getPrincipleName(),
                            school.getName(),
                            school.getAddress(),
                            school.getContactNo()
                    ));
                }
            }
        }

        return result;
    }
}
