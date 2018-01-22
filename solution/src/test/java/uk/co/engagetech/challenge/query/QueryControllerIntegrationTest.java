package uk.co.engagetech.challenge.query;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class QueryControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Test
    public void query_valid_value() {
        given().
                port(port).
                when().
                    get("/app/query/vat?amount=120").
                then().
                    statusCode(200).
                    body("vat", equalTo("20.00"));
    }

    @Test
    public void query_valid_eur_value() {
        given().
                port(port).
                when().
                    get("/app/query/vat?amount=144 EUR").
                then().
                    statusCode(200).
                    body("vat", equalTo("20.00"));
    }

    @Test
    public void query_invalid_value() {
        given().
                port(port).
                when().
                    get("/app/query/vat?amount=foo").
                then().
                    statusCode(200).
                    body("vat", equalTo("N/A"));
    }

}