package petstore.stepDefinitions;

import io.cucumber.java.en.When;
import petstore.APIcalls.StoreApi;

public class StoreStepDef extends StoreApi {

    @When("I send GET request to store inventory")
    public void i_send_get_request_to_store_inventory() {
        StoreApi.getInventory();
    }

    @When("I create a new order")
    public void i_create_a_new_order(String jsonBody) {
        StoreApi.createOrder(jsonBody);

    }

    @When("I send GET request with orderId {int}")
    public void i_send_get_request_with_order_id(int orderID) {
        StoreApi.getOrderByID(orderID);
    }

    @When("I send DELETE request with orderId {int}")
    public void i_send_delete_request_with_order_id(int orderID) {
        StoreApi.deleteOrderByID(orderID);
    }

}
