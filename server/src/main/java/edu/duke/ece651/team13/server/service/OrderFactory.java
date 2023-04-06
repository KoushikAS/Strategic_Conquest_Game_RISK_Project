package edu.duke.ece651.team13.server.service;

import edu.duke.ece651.team13.server.entity.GameEntity;
import edu.duke.ece651.team13.server.entity.OrderEntity;

public interface OrderFactory {

    void validateAndExecuteLocally(OrderEntity order, GameEntity game) throws IllegalAccessException;

}
