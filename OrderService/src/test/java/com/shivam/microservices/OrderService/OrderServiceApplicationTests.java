package com.shivam.microservices.OrderService;

//import com.shivam.microservices.OrderService.service.InventoryClient;
import com.shivam.microservices.OrderService.stub.InventoryClient;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.shaded.org.hamcrest.Matchers;

import static org.testcontainers.shaded.org.hamcrest.MatcherAssert.assertThat;


//@Import(TestcontainersConfiguration.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@AutoConfigureWireMock(port = 0)
class OrderServiceApplicationTests {

//	@ServiceConnection
//	private static final MySQLContainer mysqlContainer = new MySQLContainer("mysql:8.3.0");
//
//	@LocalServerPort
//	private int port;
//
//	@BeforeEach
//	public void setup(){
//		RestAssured.baseURI = "http://localhost:";
//		RestAssured.port=port;
//	}
//
//	static {
//		mysqlContainer.start();
//	}
//
//
//	@Test
//	void shouldPlaceOrder() {
//		String orderRequest = """
//
//				{
//				    "skuCode": "iPhone15",
//				    "quantity": "10",
//				    "price": "2000"
//				}
//
//				""";
//
//		InventoryClient.IsInStock("iPhone15", 10L);
//
//		String response = RestAssured.given()
//				.contentType("application/json")
//				.body(orderRequest)
//				.when()
//				.post("/api/v1/order")
//				.then()
//				.log().all()
//				.statusCode(201)
//				.extract()
//				.body()
//				.asString();
//		assertThat(response, Matchers.is("Order has been placed"));
////		Assertions.assertEquals("Order has been placed", response);
//	}

}
