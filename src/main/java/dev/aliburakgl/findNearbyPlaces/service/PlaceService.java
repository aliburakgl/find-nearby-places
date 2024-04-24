package dev.aliburakgl.findNearbyPlaces.service;

import dev.aliburakgl.findNearbyPlaces.api.GooglePlacesAPI;
import dev.aliburakgl.findNearbyPlaces.repository.PlaceRepository;
import dev.aliburakgl.findNearbyPlaces.model.Key;
import dev.aliburakgl.findNearbyPlaces.model.Root;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlaceService {
    @Autowired
    private PlaceRepository placeRepository;
    public Optional<Root> getplacebyId(String id){
        return placeRepository.findPlacesById(id);
    }
    public Document getplacesapi(Key key){
            String json = GooglePlacesAPI.main(key.getLongitude(), key.getLatitude(), key.getRadius());
            Document document = Document.parse(json);
            String id = createKey(key);
            document.append("_id", id);

            return document;
    }
    public void savePlaces(Document document){
            placeRepository.insert(document);
    }

    public String createKey(Key place){
            StringBuilder keyString = new StringBuilder();
            keyString.append(place.getLongitude().replace(".",""));
            keyString.append(place.getLatitude().replace(".",""));
            keyString.append(place.getRadius());
            return keyString.toString();
    }
}
