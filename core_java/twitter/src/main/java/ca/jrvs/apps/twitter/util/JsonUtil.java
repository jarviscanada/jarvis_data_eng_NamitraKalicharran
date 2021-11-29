package ca.jrvs.apps.twitter.util;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class JsonUtil {
    public static String toJson(Object object, boolean prettyJson,
                                boolean includeNull) throws JsonProcessingException {
        ObjectMapper m = new ObjectMapper();

        if (!includeNull) {
            m.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        }

        if (prettyJson) {
            m.enable(SerializationFeature.INDENT_OUTPUT);
        }
        return m.writeValueAsString(object);
    }

    public static <T> T toObjectFromJson(String json, Class<T> cl)
            throws JsonProcessingException, IOException {
        ObjectMapper m = new ObjectMapper();
        return (T) m.readValue(json, cl);
    }
}
