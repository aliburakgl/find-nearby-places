package dev.aliburakgl.findNearbyPlaces.dto;


import java.util.ArrayList;

public class Result {
    public String business_status;
    public Geometry geometry;
    public String icon;
    public String icon_background_color;
    public String icon_mask_base_uri;
    public String name;
    public OpeningHours opening_hours;
    public ArrayList<Photo> photos;
    public String place_id;
    public PlusCode plus_code;
    public double rating;
    public String reference;
    public String scope;
    public ArrayList<String> types;
    public int user_ratings_total;
    public String vicinity;
    public int price_level;
}
