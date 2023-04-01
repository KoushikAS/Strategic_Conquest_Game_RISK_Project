package edu.duke.ece651.team13.server.service;

import edu.duke.ece651.team13.server.entity.GameEntity;
import edu.duke.ece651.team13.server.entity.MapEntity;
import edu.duke.ece651.team13.server.entity.PlayerEntity;
import edu.duke.ece651.team13.server.entity.TerritoryEntity;

/**
 * The interface of Territory
 */
public interface GameService {

  GameEntity createGame(int unitNo);

  GameEntity getGame(Long gameId);

}
