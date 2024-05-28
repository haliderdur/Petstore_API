package petstore.pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class StorePOJO {

    private long id;
    private int petId;
    private int quantity;
    private String shipDate;
    private String status;
    private boolean complete;


    public static StorePOJO storePOJO;

}
