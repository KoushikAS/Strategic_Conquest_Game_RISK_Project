package edu.duke.ece651.team13.server.service;

import edu.duke.ece651.team13.server.entity.GameEntity;
import edu.duke.ece651.team13.server.enums.GameStatusEnum;

public interface GameService {

    GameEntity createGame(int unitNo);

    GameEntity getGame(Long gameId);

    GameEntity updateGameRoundAndStatus(GameEntity game, GameStatusEnum status, int roundNo);
}
