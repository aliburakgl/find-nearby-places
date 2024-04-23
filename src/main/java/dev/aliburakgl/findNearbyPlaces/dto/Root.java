package dev.aliburakgl.findNearbyPlaces.dto;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;


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
