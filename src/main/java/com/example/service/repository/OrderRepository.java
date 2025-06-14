package com.example.service.repository;

import com.example.service.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query(
            "SELECT o FROM Order o WHERE o.user.id = :userId " +
                    "AND (o.orderStatus = 'PLACED' " +
                    "OR o.orderStatus = 'CONFIRMED' " +
                    "OR o.orderStatus = 'SHIPPED' " +
                    "OR o.orderStatus = 'DELIVERED')"
    )
    List<Order> getUsersOrders(@Param("userId") Long userId);

    List<Order> getOrderByUserId(Long userId);

    Optional<Order> findOrderByPublicId(UUID uuid);
}
