package com.shivam.microservices.product_service.Controller;

import com.shivam.microservices.product_service.Dto.ProductRequest;
import com.shivam.microservices.product_service.Dto.ProductResponse;
import com.shivam.microservices.product_service.Service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductService productService;

    @PostMapping("/product")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse createProduct(@RequestBody ProductRequest productRequest){
        log.info("{}provided By the client", productRequest.toString());
        log.info("Product has been created");
        return productService.createProduct(productRequest);
    }

    @GetMapping("/products")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts(){
        log.info("Getting all products");
        return productService.getAllProducts();
    }

}
