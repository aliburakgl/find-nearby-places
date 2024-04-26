package dev.aliburakgl.findNearbyPlaces.api;

import dev.aliburakgl.findNearbyPlaces.provider.PlacesProvider;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


@Service
public class GooglePlacesAPI implements PlacesProvider {
    private static final Logger logger = LogManager.getLogger(GooglePlacesAPI.class);
    @Value("${google.api.key}")
    private  String apiKey;

    @Override
    public String getNearbyPlaces(String latitude, String longitude, String radius) {
        if (latitude == null || longitude == null || radius == null  ||
                latitude.isEmpty() || longitude.isEmpty() || radius.isEmpty()) {
            throw new IllegalArgumentException("Invalid input parameters");
        }
        String baseUrl = "https://maps.googleapis.com/maps/api/place/nearbysearch/json";
        StringBuilder location = new StringBuilder();
        location.append(latitude);
        location.append(",");
        location.append(longitude);
        String type="restaurant";

        String urlString = String.format("%s?location=%s&radius=%s&type=%s&key=%s", baseUrl, location, radius, type, apiKey);

        URL url = null;
        HttpURLConnection connection=null;
        try {
            url = new URL(urlString);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            logger.info("Building URL for Google Places API: {}", urlString);
        } catch (IOException e) {
            logger.error("Error creating URL: {}", e.getMessage());
            throw new RuntimeException(e);
        }
        try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            logger.info("Successfully fetched data from Google Places API");
            return response.toString();
        } catch (IOException e) {
            logger.error("Error fetching data from Google Places API: {}", e.getMessage());
            throw new RuntimeException(e);
        } finally {
            connection.disconnect();
            logger.info("Connection to Google Places API closed");
        }
    }

}