package petstore.endpoints;

public class UserEndpoints {

    // Endpoints of user API
    public static final String CREATE_USER = Paths.USER;
    public static final String LOGIN_USER = Paths.USER + "/login";
    public static final String LOGOUT_USER = Paths.USER + "/logout";
    public static final String GET_USER_BY_USERNAME = Paths.USER + "/{username}";
    public static final String UPDATE_USER_BY_USERNAME = Paths.USER + "/{username}";
    public static final String DELETE_USER_BY_USERNAME = Paths.USER + "/{username}";

}
