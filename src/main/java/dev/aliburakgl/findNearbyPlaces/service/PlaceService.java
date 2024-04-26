package dev.aliburakgl.findNearbyPlaces.service;

import dev.aliburakgl.findNearbyPlaces.converter.JsonConverter;
import dev.aliburakgl.findNearbyPlaces.provider.PlacesProvider;
import dev.aliburakgl.findNearbyPlaces.repository.PlaceRepository;
import dev.aliburakgl.findNearbyPlaces.model.Key;
import dev.aliburakgl.findNearbyPlaces.model.Root;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlaceService {
    @Autowired
    private final PlaceRepository placeRepository;
    @Autowired
    private final PlacesProvider placesProvider;
    private static Key staticKey;

    public Optional<Root> getNearbyPlaces(String latitude, String  longitude, String  radius) {
        staticKey =new Key(longitude ,latitude,radius);
        String id = createId(staticKey);
        Optional<Root> places = placeRepository.findPlacesById(id);
        if (places.isEmpty()){
            String jsonData = getPlacesFromGoogleApi(staticKey);
            Document documentData = getPlacesDocument(jsonData);
            savePlaces(documentData);
            places = Optional.ofNullable(JsonConverter.convertJsonToRoot(jsonData));
        }

        return places;
    }

    private String getPlacesFromGoogleApi(Key key){
            return placesProvider.getNearbyPlaces(key.getLongitude(), key.getLatitude(), key.getRadius());
    }
    private Document getPlacesDocument(String json){
        Document document = Document.parse(json);
        String id = createId(staticKey);
        document.append("_id", id);
        return document;
    }
    private void savePlaces(Document document){
            placeRepository.insert(document);
    }

    private String createId(Key key){
            StringBuilder keyString = new StringBuilder();
            keyString.append(key.getLongitude().replace(".",""));
            keyString.append(key.getLatitude().replace(".",""));
            keyString.append(key.getRadius());
            return keyString.toString();
    }

}
