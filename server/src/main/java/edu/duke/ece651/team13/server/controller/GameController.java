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

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class GameController {

    @Autowired
    private GameService gameService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private PlayerService playerService;

    @GetMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @GetMapping("/createGame/{noOfPlayer}")
    public ResponseEntity<GameEntity> createGame(@PathVariable("noOfPlayer") Integer noOfPlayer) {
        log.info("Received an /createGame");
        GameEntity gameEntity = gameService.createGame(noOfPlayer);
        return ResponseEntity.ok().body(gameEntity);
    }

    @GetMapping("/getFreeGames")
    public ResponseEntity<GamesDTO> getAvailableFreeGames() {
        log.info("Received a request /getFreeGames ");
        List<GameDTO> games =  gameService.getFreeGames();
        return ResponseEntity.ok().body(new GamesDTO(games));
    }


    @GetMapping("/getGame/{id}")
    public ResponseEntity<GameEntity> getMap(@PathVariable("id") Long id) {
        log.info("Received an /getGame/");
        try {
            GameEntity game = gameService.getGame(id);
            return ResponseEntity.ok().body(game);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping("/submitOrder")
    public ResponseEntity<String> submitOrder(
            @RequestBody OrdersDTO ordersDTO) {
        log.info("Received an /submitOrder");
        log.info("Player Id" + ordersDTO.getPlayerId());
        try {
            orderService.validateAndAddOrders(ordersDTO);
            return ResponseEntity.ok().body("Submitted successful");
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(BAD_REQUEST, e.getMessage());
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(NOT_FOUND, e.getMessage());
        }
    }

    //TODO tmp just for changing
    @GetMapping("/getOrders/{id}")
    public ResponseEntity<List<OrderEntity>> getOrders(@PathVariable("id") Long id) {
        log.info("The id recieved" + id);
        PlayerEntity player = playerService.getPlayer(id);
        List<OrderEntity> game = orderService.getOrdersByPlayer(player);
        return ResponseEntity.ok().body(game);
    }

}
