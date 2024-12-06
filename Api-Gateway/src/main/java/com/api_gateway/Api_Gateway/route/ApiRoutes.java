package com.api_gateway.Api_Gateway.route;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.util.RouteMatcher;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import java.net.URI;

import static org.springframework.cloud.gateway.server.mvc.filter.CircuitBreakerFilterFunctions.circuitBreaker;
import static org.springframework.cloud.gateway.server.mvc.filter.FilterFunctions.setPath;
import static org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions.route;
import static org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions.http;

@Configuration
public class ApiRoutes {

    @Bean
    public RouterFunction<ServerResponse> productServiceRoute() {
           return route("Product-Service")
                   .route(RequestPredicates.path(("/api/v1/product")), http("http://localhost:8080"))
                   .filter(circuitBreaker("Product-Service-Circuit-Breaker", URI.create("forward:/fallback"))) // when circuit is open
                   .build();
    }

    @Bean
    public RouterFunction<ServerResponse> productServiceApiDocsRoute(){
        return route("Product-Service-Api-Docs")
                .route(RequestPredicates.path(("/aggregate/product-service/v3/api-docs")), http("http://localhost:8080"))
                .filter(circuitBreaker("Product-Service-Circuit-Breaker-API-Docs", URI.create("forward:/fallback")))
                .filter(setPath("/api-docs")).build();
    }


    @Bean
        public RouterFunction<ServerResponse> productsRoute() {
               return route("Product-Service")
                       .route(RequestPredicates.path(("/api/v1/products")), http("http://localhost:8080"))
                       .filter(circuitBreaker("Product-Service-Circuit-Breaker-All-Products", URI.create("forward:/fallback")))
                       .build();
        }

    @Bean
    public RouterFunction<ServerResponse> orderServiceRoute() {
        return route("Order-Service")
                .route(RequestPredicates.path(("api/v1/order")), http("http://localhost:8081"))
                .filter(circuitBreaker("Order-Service-Circuit-Breaker", URI.create("forward:/fallback")))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> orderServiceApiDocsRoute() {
        return route("Order-Service-APi-Docs-Route")
                .route(RequestPredicates.path(("/aggregate/order-service/v3/api-docs")), http("http://localhost:8081"))
                .filter(circuitBreaker("Order-Service-Circuit-Breaker-API-Docs", URI.create("forward:/fallback")))
                .filter(setPath("/api-docs")).build();
    }


    @Bean
    public RouterFunction<ServerResponse> inventoryServiceRoute() {
        return route("Inventory-Service")
                .route(RequestPredicates.path(("api/v1/checkProduct")), http("http://localhost:8082"))
                .filter(circuitBreaker("Inventory-Service-Circuit-Breaker", URI.create("forward:/fallback")))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> inventoryServiceApiDocsRoute() {
        return route("")
                .route(RequestPredicates.path(("/aggregate/inventory-service/v3/api-docs")), http("http://localhost:8082"))
                .filter(circuitBreaker("Inventory-Service-Circuit-Breaker-API-Docs", URI.create("forward:/fallback")))
                .filter(setPath("/api-docs")).build();
    }

    @Bean
    public RouterFunction<ServerResponse> fallbackMethod(){
        return route("fallback-method")
                .GET("/fallback", request -> ServerResponse.status(HttpStatus.SERVICE_UNAVAILABLE).body("Service is Not available, Try again later"))
                .build();
    }
}