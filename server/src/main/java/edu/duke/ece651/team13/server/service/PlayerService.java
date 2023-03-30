package edu.duke.ece651.team13.server.service;

import edu.duke.ece651.team13.server.entity.PlayerEntity;
import edu.duke.ece651.team13.shared.enums.PlayerStatusEnum;

public interface PlayerService {
    void createPlayer(String name);

    PlayerEntity getPlayer(Long Id);

    void updatePlayerStatus(Long Id, PlayerStatusEnum status);
}
