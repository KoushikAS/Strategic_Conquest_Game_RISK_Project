package edu.duke.ece651.team13.server.order;

import edu.duke.ece651.team13.server.entity.*;
import edu.duke.ece651.team13.server.rulechecker.MoveOwnershipChecker;
import edu.duke.ece651.team13.server.rulechecker.MovePathChecker;
import edu.duke.ece651.team13.server.rulechecker.MoveUnitNumChecker;
import edu.duke.ece651.team13.server.rulechecker.RuleChecker;
import edu.duke.ece651.team13.server.service.MoveOrderNew;
import edu.duke.ece651.team13.shared.player.HumanPlayer;
import edu.duke.ece651.team13.shared.player.Player;
import edu.duke.ece651.team13.shared.territory.GameTerritory;
import edu.duke.ece651.team13.shared.territory.Territory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.ArrayList;
import java.util.List;

import static edu.duke.ece651.team13.server.MockDataUtil.getGameEntity;
import static edu.duke.ece651.team13.shared.enums.OrderMappingEnum.MOVE;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MoveOrderNewTest {

    private MoveOrderNew service; //service under test

    @BeforeEach
    void setUp() {
        service = new MoveOrderNew();
    }


    @Test
    void test_validateAndExecuteLocallySuccess() throws IllegalAccessException {
        GameEntity game = getGameEntity();
        TerritoryEntity source = game.getMap().getTerritories().get(0);
        TerritoryEntity destination = game.getMap().getTerritories().get(1);

        List<TerritoryConnectionEntity> connections = new ArrayList<>();
        connections.add(new TerritoryConnectionEntity(source, destination, 5));
        source.setConnections(connections);

        OrderEntity order = new OrderEntity();
        order.setSource(source);
        order.setDestination(destination);
        order.setOrderType(MOVE);
        order.setUnitNum(5);

        service.validateAndExecuteLocally(order, game);

        assertEquals(5, game.getMap().getTerritories().get(0).getUnitNum());
        assertEquals(15, game.getMap().getTerritories().get(1).getUnitNum());
    }

    @Test
    void test_validateAndExecuteLocallyNoConnectionError() throws IllegalAccessException {
        GameEntity game = getGameEntity();
        TerritoryEntity source = game.getMap().getTerritories().get(0);
        TerritoryEntity destination = game.getMap().getTerritories().get(1);

        List<TerritoryConnectionEntity> connections = new ArrayList<>();
        source.setConnections(connections);

        OrderEntity order = new OrderEntity();
        order.setSource(source);
        order.setDestination(destination);
        order.setOrderType(MOVE);
        order.setUnitNum(5);
        //No Connection
        assertThrows(IllegalArgumentException.class, () -> service.validateAndExecuteLocally(order, game));
    }


    @Test
    void test_validateAndExecuteLocallyExtraUnits() throws IllegalAccessException {
        GameEntity game = getGameEntity();
        TerritoryEntity source = game.getMap().getTerritories().get(0);
        TerritoryEntity destination = game.getMap().getTerritories().get(1);

        List<TerritoryConnectionEntity> connections = new ArrayList<>();
        connections.add(new TerritoryConnectionEntity(source, destination, 5));
        source.setConnections(connections);

        OrderEntity order = new OrderEntity();
        order.setSource(source);
        order.setDestination(destination);
        order.setOrderType(MOVE);
        order.setUnitNum(25);

        assertThrows(IllegalArgumentException.class, () -> service.validateAndExecuteLocally(order, game));
    }

    @Test
    void test_validateAndExecuteLocallyNotOwnerExcepetion() throws IllegalAccessException {
        GameEntity game = getGameEntity();
        PlayerEntity player1 = new PlayerEntity();
        PlayerEntity player2 = new PlayerEntity();
        player1.setId(1L);
        TerritoryEntity source = game.getMap().getTerritories().get(0);
        source.setOwner(player1);
        TerritoryEntity destination = game.getMap().getTerritories().get(1);
        destination.setOwner(player2);

        List<TerritoryConnectionEntity> connections = new ArrayList<>();
        connections.add(new TerritoryConnectionEntity(source, destination, 5));
        source.setConnections(connections);

        OrderEntity order = new OrderEntity();
        order.setSource(source);
        order.setDestination(destination);
        order.setOrderType(MOVE);
        order.setUnitNum(5);
        order.setPlayer(player2);

        assertThrows(IllegalArgumentException.class, () -> service.validateAndExecuteLocally(order, game));
        source.setOwner(player2);
        destination.setOwner(player1);
        assertThrows(IllegalArgumentException.class, () -> service.validateAndExecuteLocally(order, game));

    }

}