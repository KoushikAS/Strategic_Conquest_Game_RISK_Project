package edu.duke.ece651.team13.server.service;

import edu.duke.ece651.team13.server.entity.GameEntity;

public interface RoundService {

    Boolean isGameReadyForRoundExecution(GameEntity game);

    void playOneRound(GameEntity game);
}
