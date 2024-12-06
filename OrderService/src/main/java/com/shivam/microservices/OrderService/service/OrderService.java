package com.shivam.microservices.OrderService.service;

import com.shivam.microservices.OrderService.dto.OrderRequest;
import com.shivam.microservices.OrderService.model.Order;
import com.shivam.microservices.OrderService.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;

    public Order placeOrder(OrderRequest orderRequest) {

        boolean avl = inventoryClient.IsInInventoryStock(orderRequest.getSkuCode(), orderRequest.getQuantity());

        if (avl) {
            Order order = Order.builder()
                    .OrderNumber(UUID.randomUUID().toString())
                    .SkuCode(orderRequest.getSkuCode())
                    .price(orderRequest.getPrice())
                    .quantity(orderRequest.getQuantity())
                    .build();
            log.info("Order has been created{}", order.toString());
            orderRepository.save(order);
            log.info("Order has been saved");
            return order;
        }
        return null;
    }
}
