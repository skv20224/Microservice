package com.shivam.microservices.product_service;

import com.shivam.microservices.product_service.Dto.ProductRequest;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.MongoDBContainer;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductServiceApplicationTests {

	@ServiceConnection
	private static final MongoDBContainer monggoDbContainer = new MongoDBContainer("mongo:7.0.5");

	@LocalServerPort
	private Integer port;

	static {
		monggoDbContainer.start();
	}

	@BeforeEach
	public void setup(){
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = port;
//		monggoDbContainer.start();
	}

	@Test
	void shouldCreateProduct() {
		String productRequest =
				"{\n" +
						"    \"name\": \"Bucket 20L\",\n" +
						"    \"description\": \"Good in budget and depth\",\n" +
						"    \"price\": \"1000\"\n" +
						"}";

	RestAssured.given()
			.contentType("application/json")
			.body(productRequest)
			.when()
			.post("/api/v1/product")
			.then()
			.statusCode(201)
			.body("id", Matchers.notNullValue())
			.body("name", Matchers.equalTo("Bucket 20L"))
			.body("description", Matchers.equalTo("Good in budget and depth"))
			.body("price", Matchers.equalTo(1000));
	}

}
