package edu.duke.ece651.team13.server.service.order;

import edu.duke.ece651.team13.server.entity.GameEntity;
import edu.duke.ece651.team13.server.entity.OrderEntity;
import edu.duke.ece651.team13.server.entity.TerritoryEntity;

public interface OrderFactory {

    void validateAndExecuteLocally(OrderEntity order, GameEntity game) throws IllegalArgumentException;

    void executeOnGame(OrderEntity order, GameEntity game);
}
