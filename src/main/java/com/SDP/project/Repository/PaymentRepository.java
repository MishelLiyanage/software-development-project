package com.SDP.project.Repository;

import com.SDP.project.models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    Optional<Payment> findByOrderId(String orderId);

    @Query(value = "SELECT MONTHNAME(p.date) AS month, SUM(p.amount) AS amount " +
            "FROM payment p " +
            "GROUP BY MONTH(p.date), MONTHNAME(p.date) " +
            "ORDER BY MONTH(p.date)", nativeQuery = true)
    List<Object[]> findMonthlyRevenue();
}
