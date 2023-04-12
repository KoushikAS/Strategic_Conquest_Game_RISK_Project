package edu.duke.ece651.team13.server.service.order;

import edu.duke.ece651.team13.server.entity.*;
import edu.duke.ece651.team13.server.enums.UnitMappingEnum;
import edu.duke.ece651.team13.server.service.PlayerService;
import edu.duke.ece651.team13.server.service.UnitService;
import edu.duke.ece651.team13.server.service.order.MoveOrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static edu.duke.ece651.team13.server.MockDataUtil.getGameEntity;
import static edu.duke.ece651.team13.server.MockDataUtil.getUnitEntity;
import static edu.duke.ece651.team13.server.enums.OrderMappingEnum.MOVE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@ExtendWith(MockitoExtension.class)
class MoveOrderServiceTest {

    private MoveOrderService service; //service under test

    @Mock
    private UnitService unitService;

    @Mock
    private PlayerService playerService;

    @BeforeEach
    void setUp() {
        service = new MoveOrderService(unitService, playerService);
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
        game.getPlayers().add(player1);

        List<TerritoryConnectionEntity> connections = new ArrayList<>();
        connections.add(new TerritoryConnectionEntity(source, destination, 5));
        source.setConnections(connections);

        OrderEntity order = new OrderEntity();
        order.setSource(source);
        order.setDestination(destination);
        order.setOrderType(MOVE);
        order.setUnitType(UnitMappingEnum.LEVEL0);
        order.setUnitNum(5);
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
        source.getUnits().add(new UnitEntity(UnitMappingEnum.LEVEL0, 10));

        OrderEntity order = new OrderEntity();
        PlayerEntity player1 = new PlayerEntity();
        player1.setId(1L);
        player1.setFoodResource(140);
        source.setOwner(player1);
        destination.setOwner(player1);
        order.setSource(source);
        order.setDestination(destination);
        order.setOrderType(MOVE);
        order.setUnitType(UnitMappingEnum.LEVEL0);
        order.setUnitNum(5);
        order.setPlayer(player1);
        game.getPlayers().add(player1);
        //No Connection
        assertThrows(IllegalArgumentException.class, () -> service.validateAndExecuteLocally(order, game));
    }

    @Test
    void test_validateAndExecuteLocallyExtraUnits() throws IllegalArgumentException {
        GameEntity game = getGameEntity();
        PlayerEntity player1 = new PlayerEntity();
        player1.setId(1L);
        game.getPlayers().add(player1);
        TerritoryEntity source = game.getMap().getTerritories().get(0);
        source.getUnits().add(new UnitEntity(UnitMappingEnum.LEVEL0, 10));
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
        order.setUnitType(UnitMappingEnum.LEVEL0);
        order.setUnitNum(25);
        order.setPlayer(player1);

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
        source.getUnits().add(new UnitEntity(UnitMappingEnum.LEVEL0, 10));
        TerritoryEntity destination = game.getMap().getTerritories().get(1);
        destination.setOwner(player2);

        List<TerritoryConnectionEntity> connections = new ArrayList<>();
        connections.add(new TerritoryConnectionEntity(source, destination, 5));
        source.setConnections(connections);

        OrderEntity order = new OrderEntity();
        order.setSource(source);
        order.setDestination(destination);
        order.setOrderType(MOVE);
        order.setUnitType(UnitMappingEnum.LEVEL0);
        order.setUnitNum(5);
        order.setPlayer(player2);
        game.getPlayers().add(player2);

        assertThrows(IllegalArgumentException.class, () -> service.validateAndExecuteLocally(order, game));
        source.setOwner(player2);
        destination.setOwner(player1);
        assertThrows(IllegalArgumentException.class, () -> service.validateAndExecuteLocally(order, game));
    }

    @Test
    void test_validateAndExecuteLocally_InsufficientFood() {
        GameEntity game = getGameEntity();
        PlayerEntity player1 = new PlayerEntity();
        player1.setId(1L);
        TerritoryEntity source = game.getMap().getTerritories().get(0);
        source.setOwner(player1);
        source.getUnits().add(new UnitEntity(UnitMappingEnum.LEVEL0, 10));
        TerritoryEntity destination = game.getMap().getTerritories().get(1);
        destination.setOwner(player1);

        List<TerritoryConnectionEntity> connections = new ArrayList<>();
        connections.add(new TerritoryConnectionEntity(source, destination, 5));
        source.setConnections(connections);

        player1.setFoodResource(0);
        source.setOwner(player1);
        destination.setOwner(player1);
        OrderEntity order = new OrderEntity();
        order.setSource(source);
        order.setDestination(destination);
        order.setOrderType(MOVE);
        order.setUnitType(UnitMappingEnum.LEVEL0);
        order.setUnitNum(1);
        order.setPlayer(player1);
        game.getPlayers().add(player1);

        assertThrows(IllegalArgumentException.class, () -> service.validateAndExecuteLocally(order, game));
        try {
            service.validateAndExecuteLocally(order, game);
        } catch (IllegalArgumentException e) {
            assertEquals("Invalid move order: Player doesn't have sufficient food resource.", e.getMessage());
        }
    }

    @Test
    void test_executeOnGame() throws IllegalArgumentException {
        PlayerEntity player1 = new PlayerEntity();
        player1.setId(1L);
        player1.setFoodResource(140);

        GameEntity game = getGameEntity();
        TerritoryEntity source = game.getMap().getTerritories().get(0);
        TerritoryEntity destination = game.getMap().getTerritories().get(1);
        source.setOwner(player1);
        destination.setOwner(player1);
        game.getPlayers().add(player1);

        List<TerritoryConnectionEntity> connections = new ArrayList<>();
        connections.add(new TerritoryConnectionEntity(source, destination, 5));
        source.setConnections(connections);

        OrderEntity order = new OrderEntity();
        order.setSource(source);
        order.setDestination(destination);
        order.setOrderType(MOVE);
        order.setUnitType(UnitMappingEnum.LEVEL0);
        order.setUnitNum(5);
        order.setPlayer(player1);


        service.executeOnGame(order, game);
        assertEquals(5, game.getMap().getTerritories().get(0).getUnits().get(0).getUnitNum());
        assertEquals(15, game.getMap().getTerritories().get(1).getUnits().get(0).getUnitNum());

    }


}