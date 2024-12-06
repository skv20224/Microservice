package com.shivam.microservices.OrderService.service;

import groovy.util.logging.Slf4j;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

//@FeignClient(url = "http://localhost:8082/api/v1", value = "Inventory-Clien
@Slf4j
public interface InventoryClient {

    Logger logger = LoggerFactory.getLogger(InventoryClient.class);

    @GetExchange("/api/v1/checkProduct")
    @CircuitBreaker(name = "inventory", fallbackMethod = "fallbackMethod")
    @Retry(name = "inventory")
    public boolean IsInInventoryStock(@RequestParam String skuCode, @RequestParam Long quantity);

    default boolean fallbackMethod(String code, Integer quantity, Throwable throwable) {
        logger.info("Cannot get inventory for skucode {}, failure reason: {}", code, throwable.getMessage());
        return false;
    }

}
