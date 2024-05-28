package petstore.APIcalls;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import petstore.ResponseObject;
import petstore.endpoints.PetEndpoints;
import petstore.pojos.PetPOJO;
import petstore.utilities.ApiUtils;
import petstore.utilities.Environment;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class PetApi extends ResponseObject {

    public static Response createPet(String jsonBody) {

        response = RestAssured.given()
                .header("Authorization", "Bearer " + Environment.getVariable("apiKey"))
                .and().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(jsonBody).log().all()
                .when().post(PetEndpoints.CREATE_PET);

        response.prettyPrint();
        return response;
    }

    public static Response createPet(PetPOJO petPOJO) {

        response = RestAssured.given()
                .header("Authorization", "Bearer " + Environment.getVariable("apiKey"))
                .and().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(PetPOJO.petPOJO).log().all()
                .when().post(PetEndpoints.CREATE_PET);

        response.prettyPrint();
        return response;
    }

    public static Response updatePet() {

        PetPOJO jsonBody = ApiUtils.updatePetPojo();

        response = RestAssured.given()
                .header("Authorization", "Bearer " + Environment.getVariable("apiKey"))
                .and().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(jsonBody).log().all()
                .when().put(PetEndpoints.UPDATE_EXISTING_PET);

        response.prettyPrint();
        return response;
    }

    public static Response getPetById(long petId) {

        response = RestAssured.given()
                .header("Authorization", "Bearer " + Environment.getVariable("apiKey"))
                .accept(ContentType.JSON)
                .and().pathParam("petId", petId).log().all()
                .when().get(PetEndpoints.FIND_PET_BY_ID);

        response.prettyPrint();
        return response;
    }


    public static Response getPetByStatus(String status) {

        response = RestAssured.given()
                .header("Authorization", "Bearer " + Environment.getVariable("apiKey"))
                .accept(ContentType.JSON)
                .and().queryParam("status", status).log().all()
                .when().get(PetEndpoints.FIND_PET_BY_STATUS);

        response.prettyPrint();
        return response;
    }


    public static Response updatePetWithFormData(long petID, String name, String status) {

        response = RestAssured.given()
                .header("Authorization", "Bearer " + Environment.getVariable("apiKey"))
                .accept(ContentType.JSON)
                .contentType(ContentType.URLENC)
                .and().pathParam("petId", petID)
                .and().formParam("name", name)
                .and().formParam("status", status).log().all()
                .when().post(PetEndpoints.UPDATE_PET_WITH_DATA);

        response.prettyPrint();
        return response;
    }

    public static Response deletePet(long petID) {

        response = RestAssured.given()
                .header("Authorization", "Bearer " + Environment.getVariable("apiKey"))
                .accept(ContentType.JSON)
                .and().pathParam("petId", petID).log().all()
                .when().delete(PetEndpoints.DELETE_PET_BY_ID);

        response.prettyPrint();
        return response;

    }
}
