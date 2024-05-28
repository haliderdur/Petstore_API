package petstore.stepDefinitions;


import com.fasterxml.jackson.databind.JsonNode;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Assert;
import petstore.APIcalls.UserApi;
import petstore.pojos.ResponsePOJO;
import petstore.pojos.UserPOJO;
import petstore.utilities.ApiUtils;


public class UserStepDef extends UserApi {

    static String userJsonBody;
    static int id;
    static String username;

    @When("I create a new user")
    public void i_create_a_new_user(String jsonBody) {
        UserApi.createUser(jsonBody);
        userJsonBody = jsonBody;
    }

    @When("I send GET request with username")
    public void i_send_GET_request_with_username() {

        JsonNode node = ApiUtils.convertStringToJsonNode(userJsonBody);

        if (node != null) {
            System.out.println(node.toString());
        } else {
            System.out.println("Failed to convert JSON string to JsonNode.");
        }
        username = node.get("username").asText();
        System.out.println("username = " + username);

        UserApi.getUserByUsername(username);
    }

    @Then("I retrieve newly created user data")
    public void i_retrieve_newly_created_user_data() {
        String actualUsername = UserApi.response.body().as(UserPOJO.class).getUsername();
        String expectedUsername = username;

        System.out.println("actualUsername = " + actualUsername);
        System.out.println("expectedUsername = " + expectedUsername);

        Assert.assertEquals(expectedUsername, actualUsername);
    }

    @Then("Status code should be {int}")
    public void status_code_should_be(int statusCode) {
        MatcherAssert.assertThat(response.statusCode(), Matchers.is(statusCode));

        System.out.println("statusCode = " + response.statusCode());
    }

    @Then("I see content type json")
    public void i_see_content_type_json() {
        MatcherAssert.assertThat(UserApi.response.contentType(), Matchers.is("application/json"));
    }

    @When("I login as a registered user with username {string} and password {string}")
    public void i_login_as_a_registered_user_with_username_and_password(String username, String password) {

        UserApi.login(username, password);
        UserApi.response.prettyPrint();
        ApiUtils.convertResponseToPOJO(UserApi.response, ResponsePOJO.class);

        Assert.assertTrue(ApiUtils.convertResponseToPOJO(UserApi.response, ResponsePOJO.class).getMessage().contains("logged in user session:"));

    }

    @When("I send request with {string} username")
    public void i_send_request_with_username(String username) {
        UserApi.getUserByUsername(username);
        UserPOJO.userPOJO = ApiUtils.convertResponseToPOJO(response, UserPOJO.class);
        System.out.println(UserPOJO.userPOJO.getUsername());
        Assert.assertEquals(UserPOJO.userPOJO.getUsername(), username);
    }


    @When("I compare following json nodes with response body")
    public void i_compare_following_json_nodes_with_response_body(DataTable dataTable) {
        ApiUtils.checkMapJsonKeys(dataTable);
    }

    @Given("I get logged in user {string} info")
    public void i_get_logged_in_user_info(String username) {

        Response userData = UserApi.getUserByUsername(username);
        userData.prettyPrint();
    }

    @When("I update user with new jsonBody")
    public void i_update_user_with_new(String jsonBody) {

        userJsonBody = jsonBody;

        JsonNode node = ApiUtils.convertStringToJsonNode(userJsonBody);

        if (node != null) {
            System.out.println(node.toString());
        } else {
            System.out.println("Failed to convert JSON string to JsonNode.");
        }

        username = node.get("username").asText();
        id = node.get("id").asInt();

        System.out.println("username = " + username);
        System.out.println("id = " + id);

        UserApi.updateUser(username, jsonBody);

    }

    @When("I see following json nodes")
    public void i_see_following_json_nodes(DataTable dataTable) {
        ApiUtils.checkMapJsonKeys(dataTable);
    }

    @When("I logout user")
    public void i_logout_user() {
        UserApi.logout();
    }

    @When("I send DELETE request with {string}")
    public void i_send_delete_request_with(String username) {
        UserApi.deleteUser(username);
        ResponsePOJO.responsePOJO = ApiUtils.convertResponseToPOJO(response, ResponsePOJO.class);
        Assert.assertEquals(ResponsePOJO.responsePOJO.getMessage(), username);
    }


}
