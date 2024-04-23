package dev.aliburakgl.findNearbyPlaces;

import dev.aliburakgl.findNearbyPlaces.dto.Result;
import dev.aliburakgl.findNearbyPlaces.dto.Root;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlaceRepository extends MongoRepository<Root, ObjectId> {

    Optional<Root> findPlacesById(String id);
    void insert(Document document);

}
