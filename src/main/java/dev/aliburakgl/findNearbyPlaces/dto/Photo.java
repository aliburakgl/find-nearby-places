package dev.aliburakgl.findNearbyPlaces.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
@Document(collection = "places")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Photo {
    public int height;
    public ArrayList<String> html_attributions;
    public String photo_reference;
    public int width;
}
