package edu.duke.ece651.team13.server.service;

import edu.duke.ece651.team13.server.dto.OrdersDTO;
import edu.duke.ece651.team13.server.entity.OrderEntity;
import edu.duke.ece651.team13.server.entity.PlayerEntity;

import java.util.List;

public interface OrderService {

    List<OrderEntity> getOrdersByPlayer(PlayerEntity playerEntity);

    void validateAndAddOrders(OrdersDTO orders) throws IllegalArgumentException;
}
