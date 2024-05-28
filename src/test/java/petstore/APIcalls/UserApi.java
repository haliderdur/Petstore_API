package petstore.APIcalls;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import petstore.ResponseObject;
import petstore.endpoints.UserEndpoints;
import petstore.utilities.Environment;

public class UserApi extends ResponseObject {

    public static Response createUser(String jsonBody) {

        response = RestAssured.given()
                .header("Authorization", "Bearer " + Environment.getVariable("apiKey"))
                .and().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(jsonBody).log().all()
                .when().post(UserEndpoints.CREATE_USER);

        response.prettyPrint();
        return response;
    }

    public static Response login(String username, String password) {

        response = RestAssured.given()
                .header("Authorization", "Bearer " + Environment.getVariable("apiKey"))
                .accept(ContentType.JSON)
                .and().queryParams("username", username, "password", password)
                .and().contentType(ContentType.JSON).log().all()
                .when().get(UserEndpoints.LOGIN_USER);

        response.prettyPrint();
        return response;
    }


    public static Response getUserByUsername(String username) {

        response = RestAssured.given()
                .header("Authorization", "Bearer " + Environment.getVariable("apiKey"))
                .accept(ContentType.JSON)
                .and().pathParam("username", username)
                .and().contentType(ContentType.JSON).log().all()
                .when().get(UserEndpoints.GET_USER_BY_USERNAME);

        response.prettyPrint();
        return response;
    }


    public static Response updateUser(String username, String jsonBody) {

        response = RestAssured.given()
                .header("Authorization", "Bearer " + Environment.getVariable("apiKey"))
                .accept(ContentType.JSON)
                .and().pathParam("username", username)
                .and().contentType(ContentType.JSON)
                .and().body(jsonBody).log().all()
                .when().put(UserEndpoints.UPDATE_USER_BY_USERNAME);

        response.prettyPrint();
        return response;
    }

    public static Response logout() {

        response = RestAssured.given()
                .header("Authorization", "Bearer " + Environment.getVariable("apiKey"))
                .accept(ContentType.JSON)
                .when().get(UserEndpoints.LOGOUT_USER);

        response.prettyPrint();
        return response;
    }


    public static Response deleteUser(String username) {

        response = RestAssured.given()
                .header("Authorization", "Bearer " + Environment.getVariable("apiKey"))
                .accept(ContentType.JSON)
                .and().pathParam("username", username)
                .when().delete(UserEndpoints.DELETE_USER_BY_USERNAME);

        response.prettyPrint();
        return response;

    }
}