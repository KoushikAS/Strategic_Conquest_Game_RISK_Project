package edu.duke.ece651.team13.server.order;

import edu.duke.ece651.team13.server.entity.GameEntity;
import edu.duke.ece651.team13.server.entity.OrderEntity;
import edu.duke.ece651.team13.server.entity.PlayerEntity;
import edu.duke.ece651.team13.server.entity.TerritoryConnectionEntity;
import edu.duke.ece651.team13.server.entity.TerritoryEntity;
import edu.duke.ece651.team13.server.service.TerritoryService;
import edu.duke.ece651.team13.server.service.order.MoveOrderNew;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static edu.duke.ece651.team13.server.MockDataUtil.getGameEntity;
import static edu.duke.ece651.team13.server.MockDataUtil.getUnitEntity;
import static edu.duke.ece651.team13.shared.enums.OrderMappingEnum.MOVE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class MoveOrderNewTest {

    private MoveOrderNew service; //service under test

    @Mock
    private TerritoryService territoryService;


    @BeforeEach
    void setUp() {
        service = new MoveOrderNew(territoryService);
    }


    @Test
    void test_validateAndExecuteLocallySuccess() throws IllegalArgumentException {
        PlayerEntity player1 = new PlayerEntity();
        player1.setId(1L);
        player1.setFoodResource(140);

        GameEntity game = getGameEntity();
        TerritoryEntity source = game.getMap().getTerritories().get(0);
        TerritoryEntity destination = game.getMap().getTerritories().get(1);
        source.setOwner(player1);
        destination.setOwner(player1);

        List<TerritoryConnectionEntity> connections = new ArrayList<>();
        connections.add(new TerritoryConnectionEntity(source, destination, 5));
        source.setConnections(connections);

        OrderEntity order = new OrderEntity();
        order.setSource(source);
        order.setDestination(destination);
        order.setOrderType(MOVE);
        order.addUnit(getUnitEntity(5));
        order.setPlayer(player1);

        service.validateAndExecuteLocally(order, game);

        assertEquals(5, game.getMap().getTerritories().get(0).getUnits().get(0).getUnitNum());
        assertEquals(15, game.getMap().getTerritories().get(1).getUnits().get(0).getUnitNum());
    }

    @Test
    void test_validateAndExecuteLocallyNoConnectionError() throws IllegalArgumentException {
        GameEntity game = getGameEntity();
        TerritoryEntity source = game.getMap().getTerritories().get(0);
        TerritoryEntity destination = game.getMap().getTerritories().get(1);

        List<TerritoryConnectionEntity> connections = new ArrayList<>();
        source.setConnections(connections);

        OrderEntity order = new OrderEntity();
        PlayerEntity player1 = new PlayerEntity();
        player1.setId(1L);
        player1.setFoodResource(140);
        order.setSource(source);
        order.setDestination(destination);
        order.setOrderType(MOVE);
        order.addUnit(getUnitEntity(5));
        order.setPlayer(player1);
        //No Connection
        assertThrows(IllegalArgumentException.class, () -> service.validateAndExecuteLocally(order, game));
    }


    @Test
    void test_validateAndExecuteLocallyExtraUnits() throws IllegalArgumentException {
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
        order.addUnit(getUnitEntity(25));

        assertThrows(IllegalArgumentException.class, () -> service.validateAndExecuteLocally(order, game));
    }

    @Test
    void test_validateAndExecuteLocallyNotOwnerExcepetion() throws IllegalArgumentException {
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
        order.addUnit(getUnitEntity(5));
        order.setPlayer(player2);

        assertThrows(IllegalArgumentException.class, () -> service.validateAndExecuteLocally(order, game));
        source.setOwner(player2);
        destination.setOwner(player1);
        assertThrows(IllegalArgumentException.class, () -> service.validateAndExecuteLocally(order, game));

    }

}