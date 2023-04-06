package edu.duke.ece651.team13.server.service;

import edu.duke.ece651.team13.server.entity.GameEntity;
import edu.duke.ece651.team13.server.entity.PlayerEntity;
import edu.duke.ece651.team13.server.repository.PlayerRepository;
import edu.duke.ece651.team13.shared.enums.PlayerStatusEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoundServiceImpl implements RoundService {

    @Autowired
    private final OrderService orderService;


    @Override
    public Boolean isGameReadyForRoundExecution(GameEntity game) {
        //Checking if all the players have submitted orders
        for (PlayerEntity player : game.getPlayers()) {
            if (orderService.getOrdersByPlayer(player).size() == 0) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void playOneRound(GameEntity game) {

    }
}
