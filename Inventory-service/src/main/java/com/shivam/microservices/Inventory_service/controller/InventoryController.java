package com.shivam.microservices.Inventory_service.controller;

import com.shivam.microservices.Inventory_service.service.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@Slf4j
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping("/checkProduct")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public boolean isProductAndQuantityExist(@RequestParam String skuCode, @RequestParam Long quantity){
        log.info("Checking if product and quantity exist");
        return inventoryService.hasEnoughQuantityProduct(skuCode, quantity);
    }
}
