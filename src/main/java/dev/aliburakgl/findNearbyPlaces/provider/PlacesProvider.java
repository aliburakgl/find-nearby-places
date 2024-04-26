package dev.aliburakgl.findNearbyPlaces.provider;

public interface PlacesProvider {
    String getNearbyPlaces(String latitude, String longitude, String radius);
}