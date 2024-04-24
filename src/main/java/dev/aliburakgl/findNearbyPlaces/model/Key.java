package dev.aliburakgl.findNearbyPlaces.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
public class Key {
    private String longitude;
    private String latitude;
    private String radius;
}
