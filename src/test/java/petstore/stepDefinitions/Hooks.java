package petstore.stepDefinitions;


import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import petstore.utilities.ConfigurationReader;
import petstore.utilities.Environment;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.reset;

public class Hooks {

    static Response response;

    @Before
    public static void setup() {

        RestAssured.baseURI = ConfigurationReader.getProperty("baseURL");

    }

    @After
    public void tearDown() {
        reset();
    }
}
