package edu.duke.ece651.team13.server.controller;

import edu.duke.ece651.team13.server.dto.GameDTO;
import edu.duke.ece651.team13.server.dto.GamesDTO;
import edu.duke.ece651.team13.server.dto.OrdersDTO;
import edu.duke.ece651.team13.server.entity.GameEntity;
import edu.duke.ece651.team13.server.entity.OrderEntity;
import edu.duke.ece651.team13.server.entity.PlayerEntity;
import edu.duke.ece651.team13.server.service.GameService;
import edu.duke.ece651.team13.server.service.OrderService;
import edu.duke.ece651.team13.server.service.PlayerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequiredArgsConstructor
@Slf4j
public class GameController {

    @Autowired
    private GameService gameService;

    @Autowired
    private OrderService orderService;


    @GetMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @GetMapping("/createGame/{noOfPlayer}")
    public ResponseEntity<GameEntity> createGame(@PathVariable("noOfPlayer") Integer noOfPlayer) {
        log.info("Received an /createGame");
        try {
            GameEntity gameEntity = gameService.createGame(noOfPlayer);
            return ResponseEntity.ok().body(gameEntity);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/getFreeGames")
    public ResponseEntity<GamesDTO> getAvailableFreeGames() {
        log.info("Received a request /getFreeGames ");
        try {
            List<GameDTO> games = gameService.getFreeGames();
            return ResponseEntity.ok().body(new GamesDTO(games));
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/games/{userid}")
    public ResponseEntity<GamesDTO> getGamesOfUser(@PathVariable("userid") Long userId) {
        log.info("Received a request /games/{playerid} ");
        try {
            List<GameDTO> games = gameService.getGamesLinkedToPlayer(userId);
            return ResponseEntity.ok().body(new GamesDTO(games));
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/joinGame/{gameId}")
    public ResponseEntity<PlayerEntity> joinGame(@PathVariable("gameId") Long gameId, @RequestParam("userid") Long userId) {
        log.info("Received a request /joinGame/{gameId} ");
        try {
            PlayerEntity player = gameService.joinGame(gameId, userId);
            return ResponseEntity.ok().body(player);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/getGame/{gameId}")
    public ResponseEntity<GameEntity> getMap(@PathVariable("gameId") Long gameId) {
        log.info("Received an /getGame/");
        try {
            GameEntity game = gameService.getGame(gameId);
            return ResponseEntity.ok().body(game);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping("/submitOrder")
    public ResponseEntity<String> submitOrder(@RequestBody OrdersDTO ordersDTO, @RequestParam("playerId") Long playerId) {
        log.info("Received an /submitOrder");
        log.info("Player Id" + playerId);
        try {
            orderService.validateAndAddOrders(ordersDTO, playerId);
            return ResponseEntity.ok().body("Submitted successful");
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(BAD_REQUEST, e.getMessage());
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(NOT_FOUND, e.getMessage());
        }
    }


}
