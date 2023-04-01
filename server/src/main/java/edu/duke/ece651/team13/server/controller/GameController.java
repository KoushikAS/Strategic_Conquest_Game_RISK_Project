package edu.duke.ece651.team13.server.controller;

import edu.duke.ece651.team13.server.entity.GameEntity;
import edu.duke.ece651.team13.server.entity.MapEntity;
import edu.duke.ece651.team13.server.entity.TerritoryEntity;
import edu.duke.ece651.team13.server.service.GameService;
import edu.duke.ece651.team13.server.service.MapService;
import edu.duke.ece651.team13.server.service.TerritoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class GameController {

    @Autowired
    private GameService gameService;

    @GetMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @GetMapping("/createGame")
    public ResponseEntity<GameEntity> getGame() {

        GameEntity gameEntity = gameService.createGame(3);

        return ResponseEntity.ok().body(gameEntity);
    }

    @GetMapping("/getGame/{id}")
    public ResponseEntity<GameEntity> getMap(@PathVariable("id") Long id) {


    log.info("The id recieved" + id);
        GameEntity game = gameService.getGame(id);

        return ResponseEntity.ok().body(game);
    }


}
