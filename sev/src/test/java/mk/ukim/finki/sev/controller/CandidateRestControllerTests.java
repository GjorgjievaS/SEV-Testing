package mk.ukim.finki.sev.controller;


import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class CandidateRestControllerTests {

    private Gson gson;
    
    @Before
    public void setup() {
        gson = new Gson();
    }

    @Test
    public void testGETCandidatesStatus() {
        RequestSpecification request = RestAssured.given();
        request.baseUri("http://localhost");
        request.port(8444);
        request.basePath("/api/candidate");

        Response response = request.get();
        Assertions.assertEquals(200, response.getStatusCode());
    }

    @Test
    public void testGETCandidatesTime() {
        RequestSpecification request = RestAssured.given();
        request.baseUri("http://localhost");
        request.port(8444);
        request.basePath("/api/candidate");

        Response response = request.get();
        Assertions.assertTrue(response.getTime() < 5000);
    }

}
