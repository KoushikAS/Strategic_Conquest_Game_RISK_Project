package edu.duke.ece651.team13.server.controller;

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

    @Autowired
    private PlayerService playerService;

    @GetMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @GetMapping("/createGame")
    public ResponseEntity<GameEntity> getGame() {
        log.info("Received an /createGame");
        GameEntity gameEntity = gameService.createGame(3);
        return ResponseEntity.ok().body(gameEntity);
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
        } catch (IllegalAccessException e) {
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