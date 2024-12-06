package com.shivam.microservices.Inventory_service.service;

import com.shivam.microservices.Inventory_service.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public boolean hasEnoughQuantityProduct(String skuCode, Long quantity) {
        log.info("checking the available product and it's quantity");
        return inventoryRepository.existsBySkuCodeAndQuantityIsGreaterThanEqual(skuCode, quantity);
    }
}
