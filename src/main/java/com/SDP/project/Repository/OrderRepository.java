package com.SDP.project.Repository;

import com.SDP.project.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    Order findTopByOrderByIdDesc();

    @Query("SELECT o.id AS orderId, s.name AS schoolName, s.city, p.status, p.paymentMethod, p.date, p.time, p.amount\n" +
            "FROM Order o\n" +
            "INNER JOIN School s ON o.schoolId = s.id\n" +
            "LEFT JOIN Payment p ON o.id = p.orderId")
    List<Object[]> findAllOrdersWithDetails();

    @Query("SELECT o FROM Order o WHERE o.id = :id")
    Optional<Order> findOrderById(@Param("id") String id);

    Order findBySchoolId(int schoolId);
}