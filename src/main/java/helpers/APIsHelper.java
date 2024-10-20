package helpers;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import models.UserListResponse;

import java.io.IOException;

public class APIsHelper {

    public static <T> T mapResponse(Response response, Class<T> classType) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(response.asString(), classType);
    }
}
