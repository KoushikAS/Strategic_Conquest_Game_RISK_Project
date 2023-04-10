package edu.duke.ece651.team13.server.service;

import edu.duke.ece651.team13.server.dto.GameDTO;
import edu.duke.ece651.team13.server.entity.GameEntity;
import edu.duke.ece651.team13.server.entity.PlayerEntity;
import edu.duke.ece651.team13.server.enums.GameStatusEnum;

import java.util.List;

public interface GameService {

    List<GameDTO> getFreeGames(Long userId);

    PlayerEntity joinGame(Long gameId, Long userId);

    List<GameDTO> getGamesLinkedToPlayer(Long userId);

    GameEntity createGame(int unitNo);

    GameEntity getGame(Long gameId);

    GameEntity updateGameRoundAndStatus(GameEntity game, GameStatusEnum status, int roundNo);
}
