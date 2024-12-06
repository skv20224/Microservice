package com.shivam.microservices.Inventory_service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.MySQLContainer;

import java.nio.file.WatchEvent;
import io.restassured.RestAssured;

import static org.junit.Assert.assertFalse;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class InventoryServiceApplicationTests {

	@ServiceConnection
	private final static MySQLContainer mySqlContainer = new MySQLContainer("mysql:8.3.0");

	@LocalServerPort
	private int port;

	static {
		mySqlContainer.start();
	}

	@BeforeEach
	public void setup(){
		RestAssured.baseURI = "http://localhost:" + port;
	}


	@Test
	void shouldReturnTrue() {
		Boolean res = RestAssured.given()
				.when()
				.get("/api/v1/checkProduct?skuCode=iPhone12&quantity=1000")
				.then()
//				.statusCode(200)
				.log().all()
				.extract().response().as(Boolean.class);
		assertFalse(res);
	}

}
