package utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;

public class JsonUtils {

    private static JsonNode jsonNode;

    static {
        try {
            ObjectMapper mapper = new ObjectMapper();
            InputStream is = JsonUtils.class
                    .getClassLoader()
                    .getResourceAsStream("testdata/login.json");

            if (is == null) {
                throw new RuntimeException("JSON file not found");
            }

            jsonNode = mapper.readTree(is);

        } catch (Exception e) {
            throw new RuntimeException("Error reading JSON file", e);
        }
    }

    public static String getData(String userType, String key) {
        return jsonNode.get(userType).get(key).asText();
    }
}