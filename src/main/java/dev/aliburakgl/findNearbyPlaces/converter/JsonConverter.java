package dev.aliburakgl.findNearbyPlaces.converter;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.aliburakgl.findNearbyPlaces.model.Root;

public class JsonConverter {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static Root convertJsonToRoot(String jsonString) {
        try {
            return objectMapper.readValue(jsonString, Root.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}