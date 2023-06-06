package code.vanilson.startup.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path = "/")
public class APIController {

    @GetMapping
    public ResponseEntity<Map<Integer, String>> persons() {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "Vanilson");
        map.put(2, "sonia");
        map.put(3, "wayne");

        return ResponseEntity.ok().body(map);
    }
}
