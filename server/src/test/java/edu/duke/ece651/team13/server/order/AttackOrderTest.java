package edu.duke.ece651.team13.server.order;

import edu.duke.ece651.team13.server.entity.*;
import edu.duke.ece651.team13.server.service.AttackerService;
import edu.duke.ece651.team13.server.service.TerritoryService;
import edu.duke.ece651.team13.server.service.order.AttackOrderNew;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static edu.duke.ece651.team13.server.MockDataUtil.getGameEntity;
import static edu.duke.ece651.team13.server.enums.OrderMappingEnum.ATTACK;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class AttackOrderTest {

    private AttackOrderNew service; //service under test

    @Mock
    private TerritoryService territoryService;

    @Mock
    private AttackerService attackerService;

    @BeforeEach
    void setUp() {
        service = new AttackOrderNew(territoryService, attackerService);
    }


    @Test
    void test_validateAndExecuteLocallySuccess() throws IllegalArgumentException {
        GameEntity game = getGameEntity();
        PlayerEntity owner = new PlayerEntity();
        owner.setId(1L);
        owner.setFoodResource(150);
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
        order.setOrderType(ATTACK);
        order.setUnitNum(5);
        order.setPlayer(owner);

        service.validateAndExecuteLocally(order, game);

        assertEquals(5, game.getMap().getTerritories().get(0).getUnits().size());
    }


    @Test
    void test_validateAndExecuteLocallyNottAdjacnet() throws IllegalArgumentException {
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
        order.setOrderType(ATTACK);
        order.setUnitNum(5);
        order.setPlayer(owner);

        assertThrows(IllegalArgumentException.class, () -> service.validateAndExecuteLocally(order, game));

    }

    @Test
    void test_validateAndExecuteLocallyNotEnoughUnits() throws IllegalArgumentException {
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
        order.setOrderType(ATTACK);
        order.setUnitNum(25);
        order.setPlayer(owner);

        assertThrows(IllegalArgumentException.class, () -> service.validateAndExecuteLocally(order, game));
    }


    @Test
    void test_validateAndExecuteLocallyNotOnwedAttackingSame() throws IllegalArgumentException {
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
        order.setOrderType(ATTACK);
        order.setUnitNum(5);
        order.setPlayer(owner);

        assertThrows(IllegalArgumentException.class, () -> service.validateAndExecuteLocally(order, game));
        source.setOwner(owner);
        destination.setOwner(owner);
        assertThrows(IllegalArgumentException.class, () -> service.validateAndExecuteLocally(order, game));

    }

}