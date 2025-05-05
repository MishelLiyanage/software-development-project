package com.SDP.project.Repository;

import com.SDP.project.models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    Optional<Payment> findByOrderId(String orderId);

//    @Query("SELECT o FROM Order o WHERE o.id = :id")
//    Optional<Payment> getPaymentByOrderId(@Param("id") String id);
}
