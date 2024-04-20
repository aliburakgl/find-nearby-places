package dev.aliburakgl.findNearbyPlaces;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Places {

    @GetMapping("/api")
    public String api(){
        return "Hello World!";
    }
}
