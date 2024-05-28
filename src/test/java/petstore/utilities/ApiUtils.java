package petstore.utilities;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.datatable.DataTable;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Assert;
import petstore.ResponseObject;
import petstore.pojos.PetPOJO;

import java.util.*;
import java.util.stream.Collectors;

public class ApiUtils extends ResponseObject {


    /**
     * @param response retrieve api call response
     * @param tClass   convert the response jsonbody into class object
     * @param <T>      allows the method to be flexible and work with any type of object
     * @return returns given class object
     */
    public static <T> T convertResponseToPOJO(Response response, Class<T> tClass) {
        T POJO = response.body().as(tClass);
        System.out.println("POJO : " + POJO.toString());
        return POJO;
    }


    /**
     * @param jsonBody A string jsonbody to parse into JsonNode
     * @return to return JsonNode to manipulate jsonbody
     */
    public static JsonNode convertStringToJsonNode(String jsonBody) {

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode node;
        try {
            node = objectMapper.readTree(jsonBody);
            return node;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /*
     retrieves data from response jsonbody and stores it in Map
     then compares collected keys with datatable keys
     prints matching and missing keys
     */
    public static void checkMapJsonKeys(DataTable dataTable) {

        Map<String, Object> responseBody = response.body().as(Map.class);
        List<String> expectedKeys = dataTable.asList(String.class);
        Set<String> responseKeys = responseBody.keySet();

        List<String> missingKeys = expectedKeys.stream()
                .filter(key -> !responseKeys.contains(key))
                .collect(Collectors.toList());

        List<String> matchingKeys = expectedKeys.stream()
                .filter(key -> responseKeys.contains(key))
                .collect(Collectors.toList());

        if (missingKeys.isEmpty()) {
            System.out.println("All keys are matching");
        } else {
            System.out.println("Matching keys: " + matchingKeys);
            System.out.println("Failed keys: " + missingKeys);
        }
    }


    /*
     retrieves data from response jsonbody and stores it in List of Map
     then compares collected keys with datatable keys
     prints matching and missing keys
     */
    public static void checkListOfMapJsonKeys(DataTable dataTable) {

        List<Map<String, Object>> responseBody = response.body().as(List.class);
        System.out.println("responseBody = " + responseBody);

        List<String> expectedKeys = dataTable.asList(String.class);
        System.out.println("expectedKeys = " + expectedKeys.toString());

        Set<String> responseKeys = responseBody.get(0).keySet();
        System.out.println("responseKeys = " + responseKeys.toString());

        List<String> missingKeys = expectedKeys.stream()
                .filter(key -> !responseKeys.contains(key))
                .collect(Collectors.toList());

        List<String> matchingKeys = expectedKeys.stream()
                .filter(key -> responseKeys.contains(key))
                .collect(Collectors.toList());

        if (missingKeys.isEmpty()) {
            System.out.println("All keys are matching");
        } else {
            System.out.println("Matching keys: " + matchingKeys);
            System.out.println("Failed keys: " + missingKeys);
        }

    }


    /*
    Generates random id number
     */
    public static long idGenerator() {
        Random rnd = new Random();
        long random;

        do {
            random = rnd.nextLong();
        } while (random < 0);

        return random;
    }

    public static PetPOJO updatePetPojo() {

        PetPOJO.petPOJO = new PetPOJO();

        PetPOJO.petPOJO.setId(idGenerator());
        Map<String, Object> petCategory = new HashMap<>();
        petCategory.put("id", idGenerator());
        petCategory.put("name", "cat");
        PetPOJO.petPOJO.setCategory(petCategory);
        PetPOJO.petPOJO.setName("Casper");
        List<String> photoUrls = new ArrayList<>();
        photoUrls.add("url1");
        PetPOJO.petPOJO.setPhotoUrls(photoUrls);
        List<Map<String, Object>> petTags = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("id", idGenerator());
        map.put("name", "cato");
        petTags.add(map);
        PetPOJO.petPOJO.setTags(petTags);
        PetPOJO.petPOJO.setStatus("available");

        return PetPOJO.petPOJO;
    }

    public static PetPOJO createPetWithID(long id) {

        PetPOJO.petPOJO = new PetPOJO();
        PetPOJO.petPOJO.setId(id);

        Map<String, Object> petCategory = new HashMap<>();
        petCategory.put("id", idGenerator());
        petCategory.put("name", "cat");
        PetPOJO.petPOJO.setCategory(petCategory);
        PetPOJO.petPOJO.setName("Mavis");
        List<String> photoUrls = new ArrayList<>();
        photoUrls.add("url1");
        PetPOJO.petPOJO.setPhotoUrls(photoUrls);
        List<Map<String, Object>> petTags = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("id", idGenerator());
        map.put("name", "cato");
        petTags.add(map);
        PetPOJO.petPOJO.setTags(petTags);
        PetPOJO.petPOJO.setStatus("available");

        return PetPOJO.petPOJO;

    }
}
