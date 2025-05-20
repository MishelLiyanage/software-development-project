package com.SDP.project.Repository;

import com.SDP.project.models.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
    @Query("SELECT o FROM OrderItem o WHERE o.order.id = :orderId")
    List<OrderItem> getOrderItemsByOrderId(@Param("orderId") String orderId);

    @Query(value = "SELECT COUNT(DISTINCT oi.order_id) FROM order_item oi JOIN paper_sets ps ON oi.paper_set_id = ps.id WHERE ps.grade = 'Grade 5'", nativeQuery = true)
    int countGrade5ScholarshipOrders();

    @Query(value = "SELECT COUNT(DISTINCT oi.order_id) FROM order_item oi JOIN paper_sets ps ON oi.paper_set_id = ps.id WHERE ps.grade = 'Grade 4'", nativeQuery = true)
    int countGrade4ScholarshipOrders();

    @Query(value = "SELECT COUNT(DISTINCT oi.order_id) FROM order_item oi JOIN paper_sets ps ON oi.paper_set_id = ps.id WHERE ps.grade = 'Grade 3'", nativeQuery = true)
    int countGrade3ScholarshipOrders();

    @Query("SELECT CONCAT(p.grade, ' Scholarship ', p.category), COUNT(o) " +
            "FROM OrderItem o JOIN PaperSets p ON o.paperSetId = p.id " +
            "WHERE p.category LIKE %:keyword% " +
            "GROUP BY p.grade, p.category")
    List<Object[]> getRawOrderDistribution(@Param("keyword") String keyword);

    Optional<OrderItem> findByOrderIdAndPaperSetId(String orderId, int paperSetId);
}
