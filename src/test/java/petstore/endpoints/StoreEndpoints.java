package petstore.endpoints;

public class StoreEndpoints {

    // Endpoints of store API
    public static final String GET_STORE_INVENTORY = Paths.STORE + "/inventory";
    public static final String CREATE_STORE_ORDER = Paths.STORE + "/order";
    public static final String FIND_ORDER_BY_ORDERID = Paths.STORE + "/order/{orderId}";
    public static final String DELETE_ORDER_BY_ORDERID = Paths.STORE + "/order/{orderId}";

}
