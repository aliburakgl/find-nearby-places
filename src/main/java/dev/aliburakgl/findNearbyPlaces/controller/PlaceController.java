package dev.aliburakgl.findNearbyPlaces.controller;

import dev.aliburakgl.findNearbyPlaces.model.Key;
import dev.aliburakgl.findNearbyPlaces.model.Root;
import dev.aliburakgl.findNearbyPlaces.service.PlaceService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/places")
@RequiredArgsConstructor
public class PlaceController {
    private static final Logger logger = LogManager.getLogger(PlaceController.class);
    @Autowired
    private final PlaceService placeService;
    @GetMapping("/get")
    public ResponseEntity<Optional<Root>> getNearbyPlaces(@RequestParam("longitude") String longitude,
                                                          @RequestParam("latitude") String latitude,
                                                          @RequestParam("radius") String radius) {
        if (longitude == null || latitude == null || radius == null ||
                longitude.isEmpty() || latitude.isEmpty() || radius.isEmpty()) {
            logger.error("Invalid input parameters");
            return ResponseEntity.badRequest().body(Optional.empty());
        }
        try {
            Optional<Root> places = placeService.getNearbyPlaces(latitude, longitude, radius);
            logger.info("Nearby places successfully retrieved: {}", places);
            return ResponseEntity.ok(places);
        } catch (NumberFormatException e) {
            logger.error("Invalid Number Format: ", e);
            return ResponseEntity.badRequest().body(Optional.empty());
        } catch (Exception e) {
            logger.error("An unexpected error occurred: ", e);
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(Optional.empty());
        }
    }


    }

