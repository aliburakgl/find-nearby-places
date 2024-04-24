package dev.aliburakgl.findNearbyPlaces.model;



import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;


import java.util.ArrayList;
import java.util.List;

@Document(collection = "places")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Root {
    private String id;
    private ArrayList<Object> html_attributions;
    private String next_page_token;
    private List<Object> results;
    private String status;
}
