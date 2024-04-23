package dev.aliburakgl.findNearbyPlaces;

import dev.aliburakgl.findNearbyPlaces.converter.Converter;
import dev.aliburakgl.findNearbyPlaces.dto.Root;
import lombok.NoArgsConstructor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@NoArgsConstructor
public class GooglePlacesAPI {
    public static String  main(String lat, String lut, String radius) {
        try {
            String apiKey = "AIzaSyBOxTyi-rGThBKcFYdrVdbU8fDVIvqsVYk";
            String baseUrl = "https://maps.googleapis.com/maps/api/place/nearbysearch/json";
            StringBuilder location = new StringBuilder();
            location.append(lat);
            location.append(",");
            location.append(lut);
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
            Root root = Converter.convertJsonToRoot(response.toString());

            in.close();
            return response.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
