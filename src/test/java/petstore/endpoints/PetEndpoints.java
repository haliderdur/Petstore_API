package petstore.endpoints;

public class PetEndpoints {


    // Endpoints of pet API
    public static final String UPLOAD_PET_IMAGE = Paths.PET + "/{petId}/uploadImage";
    public static final String CREATE_PET = Paths.PET;
    public static final String UPDATE_EXISTING_PET = Paths.PET;
    public static final String FIND_PET_BY_STATUS = Paths.PET + "/findByStatus";
    public static final String FIND_PET_BY_ID = Paths.PET + "/{petId}";
    public static final String DELETE_PET_BY_ID = Paths.PET + "/{petId}";
    public static final String UPDATE_PET_WITH_DATA = Paths.PET + "/{petId}";


}
