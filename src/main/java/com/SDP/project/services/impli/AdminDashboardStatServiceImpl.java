package com.SDP.project.services.impli;

import com.SDP.project.DTOs.AdminStatDto;
import com.SDP.project.Repository.OrderItemRepository;
import com.SDP.project.Repository.OrderRepository;
import com.SDP.project.Repository.SchoolRepository;
import com.SDP.project.services.AdminDashboardStatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminDashboardStatServiceImpl implements AdminDashboardStatService {
    @Autowired
    private SchoolRepository schoolRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    public AdminStatDto getDashboardStat() {
        AdminStatDto stats = new AdminStatDto();
        stats.setRegisteredSchools(schoolRepository.countRegisteredSchools());
        stats.setTotalOrders(orderRepository.countTotalOrders());
        stats.setGrade5scholarship(orderItemRepository.countGrade5ScholarshipOrders());
        stats.setGrade4scholarship(orderItemRepository.countGrade4ScholarshipOrders());
        stats.setGrade3scholarship(orderItemRepository.countGrade3ScholarshipOrders());
        stats.setOrdersToProcess(orderRepository.countOrdersToProcess());
        return stats;
    }
}
