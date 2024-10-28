package com.example.lawyerservice.controller;


import com.example.lawyerservice.domain.Lawyer;
import com.example.lawyerservice.repository.InMemoryRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LawyerControllerTest {

    @Autowired
    InMemoryRepository repository;

    @Autowired
    LawyerController lawyerController;

    @LocalServerPort
    private Integer port;

    static MySQLContainer<?> mySQLContainer = new MySQLContainer<>(
            "mysql:9.0.1"
    );

    @BeforeAll
    static void beforeAll() {
        mySQLContainer.start();
    }

    @AfterAll
    static void afterAll() {
        mySQLContainer .stop();
    }

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mySQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", mySQLContainer::getUsername);
        registry.add("spring.datasource.password", mySQLContainer::getPassword);
    }

    @BeforeEach
    void setup(){
        RestAssured.baseURI = "http://localhost:" + port;
    }

    private final String API = "/api/lawyers/";

    Lawyer first = Lawyer.builder()
            .id(1)
            .name("test1")
            .build();

    Lawyer second = Lawyer.builder()
            .id(2)
            .name("test2")
            .build();

    @Disabled
    @Test
    void shouldSaveOneLawyer() {

        repository.deleteAll();

        String requestBody = "{\n" +
                "  \"id\": \"1\",\n" +
                "  \"name\": \"test1\",\n}";

        Response response = given()
                .contentType(ContentType.JSON)
                .and()
                .body(requestBody)
                .when()
                .post(API)
                .then()
                .extract().response();

        System.out.println(response.jsonPath().prettyPrint());

        Assertions.assertEquals(201, response.statusCode());
        Assertions.assertEquals("1", response.jsonPath().getString("id"));
        Assertions.assertEquals("test1", response.jsonPath().getString("name"));
    }

    @Disabled
    @Test
    void updateOneLawyer(){
        repository.deleteAll();

        repository.addLawyer(first);

        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get(API)
                .then()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("[test1]", response.jsonPath().getString("name"));


        String id = first.getId().toString();

        String requestBody = "{\n" +
                "  \"id\": \"" + id + "\",\n" +
                "  \"name\": \"Put\",\n}";

        System.out.println(requestBody.toString());

        Response response1 = given()
                .contentType(ContentType.JSON)
                .and()
                .body(requestBody)
                .when()
                .put(API + id)
                .then()
                .extract().response();


        System.out.println(response1.jsonPath().prettyPrint());

        Assertions.assertEquals(200, response1.statusCode());
        Assertions.assertEquals("Put", response1.jsonPath().getString("name"));

        Response response2 = given()
                .contentType(ContentType.JSON)
                .when()
                .get(API)
                .then()
                .extract().response();

        Assertions.assertEquals(200, response2.statusCode());
        Assertions.assertEquals("[Put]", response2.jsonPath().getString("name"));
    }

    @Disabled
    @Test
    void shouldFindOneLawyer(){
        repository.deleteAll();
        Lawyer saved = repository.save(first);

        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get(API + saved.getId())
                .then()
                .extract().response();

        System.out.println(response.jsonPath().prettyPrint());

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("test1", response.jsonPath().getString("name"));
    }

    @Test
    void NotFoundLawyer(){
        repository.deleteAll();
        Lawyer saved = repository.save(first);

        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get(API + (saved.getId() +10))
                .then()
                .extract().response();

        System.out.println(response.jsonPath().prettyPrint());

        Assertions.assertEquals(404, response.statusCode());
        Assertions.assertEquals("Not Found", response.jsonPath().getString("error"));
    }

    @Disabled
    @Test
    void shouldFindTwoLwayers(){
        repository.deleteAll();
        repository.addLawyer(first);
        repository.addLawyer(second);

        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get(API)
                .then()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("[test1, test2]", response.jsonPath().getString("name"));
    }

    @Disabled
    @DisplayName("DeleteById Test")
    @Test
    void shouldDeleteOneLawyer(){
        repository.deleteAll();
        Lawyer saved = repository.save(first);

        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .delete(API + saved.getId())
                .then()
                .extract().response();

        System.out.println(response.jsonPath().prettyPrint());

        Assertions.assertEquals(200, response.statusCode());


        Response response2 = given()
                .contentType(ContentType.JSON)
                .when()
                .get(API)
                .then()
                .extract().response();

        Assertions.assertEquals(200, response2.statusCode());
        Assertions.assertEquals("[]", response2.jsonPath().getString("name"));
    }

    @Disabled
    @Test
    void shouldDeleteTwoLawyers(){
        repository.deleteAll();
        repository.addLawyer(first);
        repository.addLawyer(second);

        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get(API)
                .then()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());

        Response response1 = given()
                .contentType(ContentType.JSON)
                .when()
                .delete(API)
                .then()
                .extract().response();

        System.out.println(response.jsonPath().prettyPrint());

        Assertions.assertEquals(200, response1.statusCode());

        Response response2 = given()
                .contentType(ContentType.JSON)
                .when()
                .get((API)
                .then()
                .extract().response();

        Assertions.assertEquals(200, response2.statusCode());
    }

    @Disabled
    @Test
    void UpdateWithBadData(){
        repository.deleteAll();
        Lawyer saved = repository.save(first);

        String badId = String.valueOf(saved.getId() +1000000);

        String requestBody = "{\n" +
                "  \"id\": \"" + badId + "\",\n" +
                "  \"name\": \"Bad data\",\n}";


        Response response = given()
                .contentType(ContentType.JSON)
                .and()
                .body(requestBody)
                .when()
                .put("/api/dancerInstructors/" + badId)
                .then()
                .extract().response();

        System.out.println(response.jsonPath().prettyPrint());

        Assertions.assertEquals(404, response.statusCode());
        Assertions.assertEquals("Not Found", response.jsonPath().getString("error"));
    }
}
