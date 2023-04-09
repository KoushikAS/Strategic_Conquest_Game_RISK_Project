package edu.duke.ece651.team13.server.service;

import edu.duke.ece651.team13.server.entity.GameEntity;
import edu.duke.ece651.team13.server.entity.PlayerEntity;
import edu.duke.ece651.team13.server.entity.UserEntity;
import edu.duke.ece651.team13.server.enums.PlayerStatusEnum;

import java.util.List;

public interface PlayerService {
    PlayerEntity createPlayer(String name, GameEntity game);

    PlayerEntity getPlayer(Long Id);

    List<PlayerEntity> getPlayersByUser(UserEntity user);

    PlayerEntity updatePlayerStatus(PlayerEntity player, PlayerStatusEnum status);

    PlayerEntity updatePlayerUser(PlayerEntity player, UserEntity userEntity);
}
