package edu.duke.ece651.team13.server.service;

import edu.duke.ece651.team13.server.entity.GameEntity;
import edu.duke.ece651.team13.server.entity.PlayerEntity;
import edu.duke.ece651.team13.shared.enums.PlayerStatusEnum;

public interface PlayerService {
    PlayerEntity createPlayer(String name, GameEntity game);

    PlayerEntity getPlayer(Long Id);

    PlayerEntity updatePlayerStatus(Long Id, PlayerStatusEnum status);
}