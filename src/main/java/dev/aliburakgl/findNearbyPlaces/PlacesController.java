package dev.aliburakgl.findNearbyPlaces;

import dev.aliburakgl.findNearbyPlaces.dto.Result;
import dev.aliburakgl.findNearbyPlaces.dto.Root;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/places")
public class PlacesController {
   @Autowired
    private PlaceService placeService;
    @GetMapping
   public ResponseEntity<List<Root>> getNearbyPlaces(){
       return  new ResponseEntity<List<Root>>(placeService.place(), HttpStatus.OK);
   }
   @GetMapping("/{id}")
   public ResponseEntity<Optional<Root>> getNearbyPlace(@PathVariable String id){
            if (placeService.getplacebyId(id).isEmpty()){
                Document document = placeService.getplacesapi("41.0000","23.0000","10000");//TODO
                placeService.savePlaces(document);
            }
       return  new ResponseEntity<Optional<Root>>(placeService.getplacebyId(id), HttpStatus.OK);
   }
}
