package com.shivam.microservices.OrderService.stub;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;


public class InventoryClient {

    public static void IsInStock(String skuCode, Long quantity) {
        stubFor(get(urlEqualTo("/api/v1/checkProduct?skuCode=" + skuCode + "&quantity=" + quantity))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("true")
        ));
    }
}