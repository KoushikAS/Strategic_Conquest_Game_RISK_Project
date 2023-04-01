package edu.duke.ece651.team13.server.controller;

import edu.duke.ece651.team13.server.entity.MapEntity;
import edu.duke.ece651.team13.server.entity.TerritoryEntity;
import edu.duke.ece651.team13.server.service.MapService;
import edu.duke.ece651.team13.server.service.TerritoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class TestController {

    @Autowired
    private final MapService mapService;

    @GetMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @GetMapping("/getMap")
    public ResponseEntity<MapEntity> getMap() {

        MapEntity mapEntity = mapService.createMap(3);
        MapEntity map = mapService.getMap(mapEntity.getId());
        log.info("Count "+ map.getTerritories());
        return ResponseEntity.ok().body(map);
    }

}
