package petstore.APIcalls;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import petstore.ResponseObject;
import petstore.endpoints.StoreEndpoints;
import petstore.utilities.Environment;

public class StoreApi extends ResponseObject {


    public static Response getInventory() {

        response = RestAssured.given()
                .header("Authorization", "Bearer " + Environment.getVariable("apiKey"))
                .and().accept(ContentType.JSON)
                .when().get(StoreEndpoints.GET_STORE_INVENTORY);

        response.prettyPrint();
        return response;
    }

    public static Response createOrder(String jsonBody) {

        response = RestAssured.given()
                .header("Authorization", "Bearer " + Environment.getVariable("apiKey"))
                .and().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(jsonBody).log().all()
                .when().post(StoreEndpoints.CREATE_STORE_ORDER);

        response.prettyPrint();
        return response;
    }

    public static Response getOrderByID(int orderID) {

        response = RestAssured.given()
                .header("Authorization", "Bearer " + Environment.getVariable("apiKey"))
                .and().accept(ContentType.JSON)
                .and().pathParam("orderId", orderID).log().all()
                .when().get(StoreEndpoints.FIND_ORDER_BY_ORDERID);

        response.prettyPrint();
        return response;
    }

    public static Response deleteOrderByID(int orderID) {

        response = RestAssured.given()
                .header("Authorization", "Bearer " + Environment.getVariable("apiKey"))
                .and().accept(ContentType.JSON)
                .and().pathParam("orderId", orderID).log().all()
                .when().delete(StoreEndpoints.DELETE_ORDER_BY_ORDERID);

        response.prettyPrint();
        return response;
    }
}
