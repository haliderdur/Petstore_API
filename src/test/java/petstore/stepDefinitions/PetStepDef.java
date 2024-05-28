package petstore.stepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.junit.Assert;
import petstore.APIcalls.PetApi;
import petstore.APIcalls.UserApi;
import petstore.pojos.PetPOJO;
import petstore.pojos.ResponsePOJO;
import petstore.pojos.UserPOJO;
import petstore.utilities.ApiUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

public class PetStepDef extends PetApi {

    static long petId;

    @When("I create a new pet")
    public void i_create_a_new_pet(String jsonBody) {
        PetApi.createPet(jsonBody);
    }


    @Given("I update pet with new jsonBody")
    public void i_Update_Pet_With_New_JsonBody() {

        PetApi.updatePet();
        petId = response.jsonPath().getLong("id");

        Assert.assertTrue(response.jsonPath().get("category.name")
                .equals(PetPOJO.petPOJO.getCategory().get("name").toString()));
    }


    @When("I send GET request with ID")
    public void i_Send_Get_Request_With_id() {

        PetApi.getPetById(petId);
        System.out.println(petId);
        System.out.println(PetPOJO.petPOJO.getId());

        Assert.assertEquals(petId, PetPOJO.petPOJO.getId());

    }

    @When("I send GET request with status")
    public void i_Send_Get_Request_With_Status(DataTable status) {

        List<String> statusList = status.asList();

        for (String eachStatus : statusList) {

            PetApi.getPetByStatus(eachStatus);
            List<PetPOJO> petPOJOList = response.jsonPath().getList("", PetPOJO.class);

            Assert.assertTrue(petPOJOList.stream().allMatch(p -> p.getStatus().equals(eachStatus)));
        }
    }

    @When("I see following json keys")
    public void i_see_following_json_keys(DataTable dataTable) {
        ApiUtils.checkListOfMapJsonKeys(dataTable);
    }

    @Given("I create a new pet with {long}")
    public void iCreateANewPetWithId(long petId) {
        ApiUtils.createPetWithID(petId);
        PetApi.createPet(PetPOJO.petPOJO);
    }


    @When("I update pet {long},{string},{string}")
    public void i_update_pet(long petID, String name, String status) {
        PetApi.updatePetWithFormData(petID, name, status);
        ResponsePOJO.responsePOJO = ApiUtils.convertResponseToPOJO(response, ResponsePOJO.class);
        Assert.assertEquals(ResponsePOJO.responsePOJO.getMessage(), String.valueOf(petID));

    }

    @When("I send DELETE request with {long}")
    public void i_send_delete_request_with_id(long petID) {
        PetApi.deletePet(petID);
        ResponsePOJO.responsePOJO = ApiUtils.convertResponseToPOJO(response, ResponsePOJO.class);
        Assert.assertEquals(ResponsePOJO.responsePOJO.getMessage(), String.valueOf(petID));
    }

}
