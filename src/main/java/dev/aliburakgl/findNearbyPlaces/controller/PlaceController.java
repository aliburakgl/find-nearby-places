package dev.aliburakgl.findNearbyPlaces.controller;

import dev.aliburakgl.findNearbyPlaces.model.Key;
import dev.aliburakgl.findNearbyPlaces.model.Root;
import dev.aliburakgl.findNearbyPlaces.service.PlaceService;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/places")
@CrossOrigin
public class PlaceController {
    @Autowired
    private PlaceService placeService;
    @GetMapping("/get")
    public ResponseEntity<Optional<Root>> getNearbyPlace(@RequestParam("longitude") String longitude, @RequestParam("latitude") String latitude, @RequestParam("radius") String radius){
        Key key = new Key(longitude,latitude,radius);

        String id = placeService.createKey(key);
        if (placeService.getplacebyId(id).isEmpty()){
            Document document = placeService.getplacesapi(key);
            placeService.savePlaces(document);
        }
        return  new ResponseEntity<Optional<Root>>(placeService.getplacebyId(id), HttpStatus.OK);
    }

}
