package dev.aliburakgl.findNearbyPlaces.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.ArrayList;

@Document(collection = "places")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    public String business_status;
    @DocumentReference
    public Geometry geometry;
    public String icon;
    public String icon_background_color;
    public String icon_mask_base_uri;
    public String name;
    @DocumentReference
    public OpeningHours opening_hours;
    @DocumentReference
    public ArrayList<Photo> photos;
    public String place_id;
    @DocumentReference
    public PlusCode plus_code;
    public double rating;
    public String reference;
    public String scope;
    @DocumentReference
    public ArrayList<String> types;
    public int user_ratings_total;
    public String vicinity;
    public int price_level;
}
