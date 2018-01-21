package uk.co.engagetech.challenge.expenses;

import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ExpensesControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private ExpensesRepository repository;

    @Before
    public void setup() {
        repository.deleteAll();
    }

    @Test
    public void no_initial_expenses() {
        given().
                port(port).
                when().
                    get("/app/expenses").
                then().
                    statusCode(200).
                    body("", equalTo(Collections.EMPTY_LIST));
    }

    @Test
    public void expenses_creation() {

        ExpenseResource exp1 = new ExpenseResource();
        exp1.setDate("12/12/2017");
        exp1.setAmount("100");
        exp1.setReason("Foo bar");

        given().
                port(port).
                contentType(ContentType.JSON).
                body(exp1).
                when().
                    post("/app/expenses").
                then().
                    statusCode(200).
                    body("date", equalTo("12/12/2017")).
                    body("amount", equalTo("100.00")).
                    body("reason", equalTo("Foo bar"));

        given().
                port(port).
                when().
                    get("/app/expenses").
                then().
                    statusCode(200).
                    body("[0].date", equalTo("12/12/2017")).
                    body("[0].amount", equalTo("100.00")).
                    body("[0].reason", equalTo("Foo bar"));
    }
}