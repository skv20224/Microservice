package com.shivam.microservices.OrderService.controller;

import com.shivam.microservices.OrderService.dto.OrderRequest;
import com.shivam.microservices.OrderService.model.Order;
import com.shivam.microservices.OrderService.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/order")
    @ResponseStatus(HttpStatus.CREATED)
    public String placeOrder(@RequestBody OrderRequest orderRequest) {
        log.info("Placing order request: {}", orderRequest);
        Order order = orderService.placeOrder(orderRequest);
        return order!=null ? "Order has been placed" : "Order couldn't be placed due to item missing or quntity not available";
    }

}
