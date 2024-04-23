package dev.aliburakgl.findNearbyPlaces;

import dev.aliburakgl.findNearbyPlaces.dto.Root;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlaceService {
    @Autowired
    private PlaceRepository placeRepository;
        public List<Root> place(){
            return placeRepository.findAll();
        };

        public Optional<Root> getplace(ObjectId id){
            return placeRepository.findById(id);
        }

    public Optional<Root> getplacebyId(String id){
        return placeRepository.findPlacesById(id);
    }
    public Document getplacesapi(String lat, String lut, String radius){
            String json = GooglePlacesAPI.main(lat,lut,radius);
            Document document = Document.parse(json);
            StringBuilder key = new StringBuilder();
            key.append(lat.replace(".",""));
            key.append(lut.replace(".",""));
            key.append(radius);
            document.append("_id", key.toString());

            return document;
    }
    public void savePlaces(Document document){
            placeRepository.insert(document);
    }

}
