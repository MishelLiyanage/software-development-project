package com.SDP.project.Repository;

import com.SDP.project.models.Order;
import com.SDP.project.models.OrderItem;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
    @Query("SELECT o FROM OrderItem o WHERE o.order.id = :orderId")
    List<OrderItem> getOrderItemsByOrderId(@Param("orderId") String orderId);
}
