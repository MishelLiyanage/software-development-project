package com.SDP.project.Repository;

import com.SDP.project.models.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
    @Query("SELECT o FROM OrderItem o WHERE o.order.id = :orderId")
    List<OrderItem> getOrderItemsByOrderId(@Param("orderId") String orderId);
}
