package edu.duke.ece651.team13.server.order;

import edu.duke.ece651.team13.server.entity.*;
import edu.duke.ece651.team13.server.service.AttackOrderNew;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static edu.duke.ece651.team13.server.MockDataUtil.getGameEntity;
import static edu.duke.ece651.team13.shared.enums.OrderMappingEnum.MOVE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class AttackOrderTest {

    private AttackOrderNew service; //service under test

    @BeforeEach
    void setUp() {
        service = new AttackOrderNew();
    }


    @Test
    void test_validateAndExecuteLocallySuccess() throws IllegalAccessException {
        GameEntity game = getGameEntity();
        PlayerEntity owner = new PlayerEntity();
        owner.setId(1L);
        PlayerEntity opponent = new PlayerEntity();
        opponent.setId(2L);
        TerritoryEntity source = game.getMap().getTerritories().get(0);
        source.setOwner(owner);
        TerritoryEntity destination = game.getMap().getTerritories().get(1);
        destination.setOwner(opponent);

        List<TerritoryConnectionEntity> connections = new ArrayList<>();
        connections.add(new TerritoryConnectionEntity(source, destination, 5));
        source.setConnections(connections);

        OrderEntity order = new OrderEntity();
        order.setSource(source);
        order.setDestination(destination);
        order.setOrderType(MOVE);
        order.setUnitNum(5);
        order.setPlayer(owner);

        service.validateAndExecuteLocally(order, game);

        assertEquals(5, game.getMap().getTerritories().get(0).getUnitNum());
    }


    @Test
    void test_validateAndExecuteLocallyNottAdjacnet() throws IllegalAccessException {
        GameEntity game = getGameEntity();
        PlayerEntity owner = new PlayerEntity();
        owner.setId(1L);
        PlayerEntity opponent = new PlayerEntity();
        opponent.setId(2L);
        TerritoryEntity source = game.getMap().getTerritories().get(0);
        source.setOwner(owner);
        TerritoryEntity destination = game.getMap().getTerritories().get(1);
        destination.setOwner(opponent);

        List<TerritoryConnectionEntity> connections = new ArrayList<>();

        source.setConnections(connections);

        OrderEntity order = new OrderEntity();
        order.setSource(source);
        order.setDestination(destination);
        order.setOrderType(MOVE);
        order.setUnitNum(5);
        order.setPlayer(owner);

        assertThrows(IllegalArgumentException.class, () -> service.validateAndExecuteLocally(order, game));

    }

    @Test
    void test_validateAndExecuteLocallyNotEnoughUnits() throws IllegalAccessException {
        GameEntity game = getGameEntity();
        PlayerEntity owner = new PlayerEntity();
        owner.setId(1L);
        PlayerEntity opponent = new PlayerEntity();
        opponent.setId(2L);
        TerritoryEntity source = game.getMap().getTerritories().get(0);
        source.setOwner(owner);
        TerritoryEntity destination = game.getMap().getTerritories().get(1);
        destination.setOwner(opponent);

        List<TerritoryConnectionEntity> connections = new ArrayList<>();
        connections.add(new TerritoryConnectionEntity(source, destination, 5));
        source.setConnections(connections);

        OrderEntity order = new OrderEntity();
        order.setSource(source);
        order.setDestination(destination);
        order.setOrderType(MOVE);
        order.setUnitNum(25);
        order.setPlayer(owner);

        assertThrows(IllegalArgumentException.class, () -> service.validateAndExecuteLocally(order, game));

    }


    @Test
    void test_validateAndExecuteLocallyNotOnwedAttackingSame() throws IllegalAccessException {
        GameEntity game = getGameEntity();
        PlayerEntity owner = new PlayerEntity();
        owner.setId(1L);
        PlayerEntity opponent = new PlayerEntity();
        opponent.setId(2L);
        TerritoryEntity source = game.getMap().getTerritories().get(0);
        source.setOwner(opponent);
        TerritoryEntity destination = game.getMap().getTerritories().get(1);
        destination.setOwner(opponent);

        List<TerritoryConnectionEntity> connections = new ArrayList<>();
        connections.add(new TerritoryConnectionEntity(source, destination, 5));
        source.setConnections(connections);

        OrderEntity order = new OrderEntity();
        order.setSource(source);
        order.setDestination(destination);
        order.setOrderType(MOVE);
        order.setUnitNum(5);
        order.setPlayer(owner);

        assertThrows(IllegalArgumentException.class, () -> service.validateAndExecuteLocally(order, game));
        source.setOwner(owner);
        destination.setOwner(owner);
        assertThrows(IllegalArgumentException.class, () -> service.validateAndExecuteLocally(order, game));

    }

}