package com.SDP.project.Repository;

import com.SDP.project.models.Order;
import com.SDP.project.models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    Optional<Payment> findByOrderId(String orderId);

//    @Query("SELECT o FROM Order o WHERE o.id = :id")
//    Optional<Payment> getPaymentByOrderId(@Param("id") String id);
}
