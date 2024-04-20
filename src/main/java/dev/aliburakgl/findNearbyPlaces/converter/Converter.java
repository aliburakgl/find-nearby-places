package dev.aliburakgl.findNearbyPlaces.converter;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.aliburakgl.findNearbyPlaces.dto.Result;
import dev.aliburakgl.findNearbyPlaces.dto.Root;

import java.util.ArrayList;

public class Converter {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static Root convertJsonToRoot(String jsonString) {
        try {
            return objectMapper.readValue(jsonString, Root.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ArrayList<Result> convertJsonToResults(String jsonString) {
        try {
            Root root = objectMapper.readValue(jsonString, Root.class);
            return root.results;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

