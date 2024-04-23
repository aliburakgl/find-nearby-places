package dev.aliburakgl.findNearbyPlaces.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "places")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Viewport {
    public Northeast northeast;
    public Southwest southwest;
}
