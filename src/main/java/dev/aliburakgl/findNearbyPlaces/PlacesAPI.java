package dev.aliburakgl.findNearbyPlaces;

import dev.aliburakgl.findNearbyPlaces.converter.Converter;
import dev.aliburakgl.findNearbyPlaces.dto.Result;
import lombok.NoArgsConstructor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

@NoArgsConstructor
public class PlacesAPI {
    public static String  main() {
        try {
            String apiKey = "AIzaSyBOxTyi-rGThBKcFYdrVdbU8fDVIvqsVYk";
            String baseUrl = "https://maps.googleapis.com/maps/api/place/nearbysearch/json";
            String location = "41.0082,28.9784";
            String radius = "1000";
            String type = "restaurant";

            String urlString = String.format("%s?location=%s&radius=%s&type=%s&key=%s", baseUrl, location, radius, type, apiKey);
            URL url = new URL(urlString);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            ArrayList<Result> results = Converter.convertJsonToResults(response.toString());
            results.stream()
                    .map(result -> result.name)
                    .forEach(System.out::println);
            System.out.println(response.toString());
            return response.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
