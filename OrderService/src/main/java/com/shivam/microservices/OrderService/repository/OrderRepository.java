package com.shivam.microservices.OrderService.repository;

import com.shivam.microservices.OrderService.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
