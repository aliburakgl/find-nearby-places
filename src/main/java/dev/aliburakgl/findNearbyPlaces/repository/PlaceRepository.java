package dev.aliburakgl.findNearbyPlaces.repository;

import dev.aliburakgl.findNearbyPlaces.model.Root;
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
