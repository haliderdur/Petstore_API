package petstore.pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponsePOJO {

    private int code;
    private String type;
    private String message;

    public static ResponsePOJO responsePOJO;

}
