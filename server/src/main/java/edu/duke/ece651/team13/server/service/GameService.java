package edu.duke.ece651.team13.server.service;

import edu.duke.ece651.team13.server.entity.GameEntity;

public interface GameService {

    GameEntity createGame(int unitNo);

    GameEntity getGame(Long gameId);

}
