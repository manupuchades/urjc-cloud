package es.codeurjc.api.rest.test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpStatus;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.given;
import static io.restassured.path.json.JsonPath.from;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("Functional REST API tests")
class ApiRestTestApplicationTests {

    static final String SUCCESSFUL_STATUS = "APPROVED";
    static final String FAILURE_STATUS = "REJECTED";

    static final String CREDIT_REJECTION = "INSUFFICIENT_CREDIT";
    static final String DELIVERY_REJECTION = "NO_CAPACITY";
    static final String ALLOCATION_REJECTION = "SOLD_OUT";

    static final int gateway_port = 9090;

    Response customer;
    Response product;
    Response city;

    @BeforeAll
    void setUp() throws Exception {
        RestAssured.baseURI = "http://localhost:" + gateway_port;
        RestAssured.port = gateway_port;

        customer = createCustomer("Test Customer " + RandomStringUtils.randomAlphabetic(10), 100);
        product = createProduct("Test Product" + RandomStringUtils.randomAlphabetic(10), "Test Reference" + RandomStringUtils.randomAlphabetic(10), 500);
        city = createCity("Test City " + RandomStringUtils.randomAlphabetic(10), 20);
    }

    @Test
    @DisplayName("Succesfull Order")
    void succesfullOrder() throws Exception {
        customer.then()
                .statusCode(HttpStatus.SC_CREATED)
                .body("customerId", notNullValue());
        String customerId = from(customer.getBody().asString()).get("customerId");

        product.then()
                .statusCode(HttpStatus.SC_CREATED)
                .body("inventoryId", notNullValue());
        String productId = from(product.getBody().asString()).get("inventoryId");

        city.then()
                .statusCode(HttpStatus.SC_CREATED)
                .body("cityId", notNullValue());
        String cityId = from(city.getBody().asString()).get("cityId");

        Response storedProduct = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/v1/products/" + productId)
                .andReturn();

        Response order = createOrder(10,
                customerId,
                cityId,
                from(storedProduct.getBody().asString()).get("name"),
                from(storedProduct.getBody().asString()).get("reference"), 2);

        order.then()
                .statusCode(HttpStatus.SC_CREATED)
                .body("orderId", notNullValue());

        String orderId = from(order.getBody().asString()).get("orderId");

        Thread.sleep(4000);
        Response responseOrderState = getOrderState(orderId);

        responseOrderState
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("orderState", equalTo(SUCCESSFUL_STATUS));
    }

    @Test
    @DisplayName("Order Rejection : Allocation failure")
    void allocationFailure() throws Exception {
        customer.then()
                .statusCode(HttpStatus.SC_CREATED)
                .body("customerId", notNullValue());
        String customerId = from(customer.getBody().asString()).get("customerId");

        product.then()
                .statusCode(HttpStatus.SC_CREATED)
                .body("inventoryId", notNullValue());
        String productId = from(product.getBody().asString()).get("inventoryId");

        city.then()
                .statusCode(HttpStatus.SC_CREATED)
                .body("cityId", notNullValue());
        String cityId = from(city.getBody().asString()).get("cityId");

        Response storedProduct = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/v1/products/" + productId)
                .andReturn();

        Response order = createOrder(10,
                customerId,
                cityId,
                from(storedProduct.getBody().asString()).get("name"),
                from(storedProduct.getBody().asString()).get("reference"), 800);

        order.then()
                .statusCode(HttpStatus.SC_CREATED)
                .body("orderId", notNullValue());

        String orderId = from(order.getBody().asString()).get("orderId");

        Thread.sleep(4000);
        Response responseOrderState = getOrderState(orderId);

        responseOrderState
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("orderState", equalTo(FAILURE_STATUS))
                .body("rejectionReason", equalTo(ALLOCATION_REJECTION));
    }

    @Test
    @DisplayName("Order Rejection : Delivery failure")
    void deliveryFailure() throws Exception {
        customer.then()
                .statusCode(HttpStatus.SC_CREATED)
                .body("customerId", notNullValue());
        String customerId = from(customer.getBody().asString()).get("customerId");

        product.then()
                .statusCode(HttpStatus.SC_CREATED)
                .body("inventoryId", notNullValue());
        String productId = from(product.getBody().asString()).get("inventoryId");

        city.then()
                .statusCode(HttpStatus.SC_CREATED)
                .body("cityId", notNullValue());
        String cityId = from(city.getBody().asString()).get("cityId");

        Response storedProduct = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/v1/products/" + productId)
                .andReturn();

        Response order = createOrder(10,
                customerId,
                cityId,
                from(storedProduct.getBody().asString()).get("name"),
                from(storedProduct.getBody().asString()).get("reference"), 40);

        order.then()
                .statusCode(HttpStatus.SC_CREATED)
                .body("orderId", notNullValue());

        String orderId = from(order.getBody().asString()).get("orderId");

        Thread.sleep(4000);
        Response responseOrderState = getOrderState(orderId);

        responseOrderState
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("orderState", equalTo(FAILURE_STATUS))
                .body("rejectionReason", equalTo(DELIVERY_REJECTION));
    }

    @Test
    @DisplayName("Order Rejection : Credit failure")
    void creditFailure() throws Exception {
        customer.then()
                .statusCode(HttpStatus.SC_CREATED)
                .body("customerId", notNullValue());
        String customerId = from(customer.getBody().asString()).get("customerId");

        product.then()
                .statusCode(HttpStatus.SC_CREATED)
                .body("inventoryId", notNullValue());
        String productId = from(product.getBody().asString()).get("inventoryId");

        city.then()
                .statusCode(HttpStatus.SC_CREATED)
                .body("cityId", notNullValue());
        String cityId = from(city.getBody().asString()).get("cityId");

        Response storedProduct = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/v1/products/" + productId)
                .andReturn();

        Response order = createOrder(200,
                customerId,
                cityId,
                from(storedProduct.getBody().asString()).get("name"),
                from(storedProduct.getBody().asString()).get("reference"), 2);

        order.then()
                .statusCode(HttpStatus.SC_CREATED)
                .body("orderId", notNullValue());

        String orderId = from(order.getBody().asString()).get("orderId");

        Thread.sleep(4000);
        Response responseOrderState = getOrderState(orderId);

        responseOrderState
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("orderState", equalTo(FAILURE_STATUS))
                .body("rejectionReason", equalTo(CREDIT_REJECTION));
    }

    private Response createCustomer(String name, int creditLimit) throws JSONException {
        String customer = new JSONObject()
                .put("name", name)
                .put("creditLimit", creditLimit)
                .toString();

        return given()
                .contentType(ContentType.JSON)
                .body(customer)
                .when()
                .post("/api/v1/customers")
                .andReturn();
    }

    private Response createProduct(String name, String reference, int stockQuantity) throws JSONException {
        String product = new JSONObject()
                .put("name", name)
                .put("reference", reference)
                .put("stockQuantity", stockQuantity)
                .toString();

        return given()
                .contentType(ContentType.JSON)
                .body(product)
                .when()
                .post("/api/v1/products")
                .andReturn();
    }

    private Response createCity(String name, int capacity) throws JSONException {
        String city = new JSONObject()
                .put("name", name)
                .put("capacity", capacity)
                .toString();

        return given()
                .contentType(ContentType.JSON)
                .body(city)
                .when()
                .post("/api/v1/cities")
                .andReturn();
    }

    private Response createOrder(int orderTotal, String customerId, String cityId, String productName, String productReference, int quantity) throws JSONException {
        String order = new JSONObject()
                .put("orderTotal", orderTotal)
                .put("customerId", customerId)
                .put("cityId", cityId)
                .put("productName", productName)
                .put("productReference", productReference)
                .put("quantity", quantity)
                .toString();

        return given()
                .contentType(ContentType.JSON)
                .body(order)
                .when()
                .post("/api/v1/orders")
                .andReturn();
    }

    private Response getOrderState(String orderId) {
        return given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/v1/orders/" + orderId)
                .andReturn();
    }

}
