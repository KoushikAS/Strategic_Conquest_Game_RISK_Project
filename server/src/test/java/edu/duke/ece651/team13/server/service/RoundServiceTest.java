package edu.duke.ece651.team13.server.service;

import edu.duke.ece651.team13.server.entity.GameEntity;
import edu.duke.ece651.team13.server.entity.OrderEntity;
import edu.duke.ece651.team13.server.entity.PlayerEntity;
import edu.duke.ece651.team13.server.entity.TerritoryEntity;
import edu.duke.ece651.team13.server.enums.GameStatusEnum;
import edu.duke.ece651.team13.server.service.order.AttackOrderService;
import edu.duke.ece651.team13.server.service.order.MoveOrderService;
import edu.duke.ece651.team13.server.enums.OrderMappingEnum;
import edu.duke.ece651.team13.server.enums.PlayerStatusEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static edu.duke.ece651.team13.server.MockDataUtil.getGameEntity;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class RoundServiceTest {

    private RoundService service; //service under test

    @Mock
    private OrderService orderService;

    @Mock
    private MoveOrderService moveOrder;

    @Mock
    private AttackOrderService attackOrder;

    @Mock
    private CombatResolutionService combatResolutionService;

    @Mock
    private TerritoryService territoryService;

    @Mock
    private PlayerService playerService;

    @Mock
    private GameService gameService;

    @BeforeEach
    void setUp() {
        service = new RoundServiceImpl(orderService, moveOrder, attackOrder, combatResolutionService, territoryService, playerService, gameService);
    }

//    @Test
//    void isGameReadyForRoundExecutionTest() {
//        GameEntity gameEntity = getGameEntity();
//        PlayerEntity red = getPlayerEntity();
//        PlayerEntity blue = getPlayerEntity();
//        PlayerEntity green = getPlayerEntity();
//        green.setStatus(PlayerStatusEnum.LOSE);
//
//        gameEntity.getPlayers().add(red);
//        gameEntity.getPlayers().add(blue);
//        gameEntity.getPlayers().add(green);
//
//        List<OrderEntity> orders = new ArrayList<>();
//        orders.add(new OrderEntity());
//        when(orderService.getOrdersByPlayer(red)).thenReturn(Collections.emptyList());
//        when(orderService.getOrdersByPlayer(blue)).thenReturn(orders);
//        when(orderService.getOrdersByPlayer(red)).thenReturn(Collections.emptyList());
//        assertFalse(service.isGameReadyForRoundExecution(gameEntity));
//
//        when(orderService.getOrdersByPlayer(red)).thenReturn(orders);
//        assertTrue(service.isGameReadyForRoundExecution(gameEntity));
//    }

    @Test
    void playOneRoundTest_InitialRound() {
        GameEntity gameEntity = getGameEntity();
        gameEntity.setRoundNo(0);

        PlayerEntity red = new PlayerEntity("red");
        PlayerEntity blue = new PlayerEntity("blue");
        gameEntity.getPlayers().add(red);
        gameEntity.getPlayers().add(blue);

        List<OrderEntity> orders = new ArrayList<>();
        OrderEntity order = new OrderEntity();
        order.setOrderType(OrderMappingEnum.MOVE);
        orders.add(order);
        when(orderService.getOrdersByPlayer(red)).thenReturn(orders);
        when(orderService.getOrdersByPlayer(blue)).thenReturn(Collections.emptyList());
        when(gameService.getGame(1L)).thenReturn(gameEntity);

        service.playOneRound(1L);

        verify(gameService, times(1)).updateGameRoundAndStatus(gameEntity, GameStatusEnum.PLAYING, 1);
    }

    @Test
    void playOneRoundTest_NormalRound() {
        GameEntity gameEntity = getGameEntity();
        gameEntity.setRoundNo(1);


        PlayerEntity red = new PlayerEntity("red");
        PlayerEntity blue = new PlayerEntity("blue");
        gameEntity.getPlayers().add(red);
        gameEntity.getPlayers().add(blue);

        List<OrderEntity> orders = new ArrayList<>();
        OrderEntity order = new OrderEntity();
        order.setOrderType(OrderMappingEnum.MOVE);
        orders.add(order);

        List<TerritoryEntity> territoryEntityList = new ArrayList<>();
        TerritoryEntity territoryEntity = new TerritoryEntity();
        territoryEntity.setOwner(red);
        territoryEntityList.add(territoryEntity);

        when(orderService.getOrdersByPlayer(red)).thenReturn(orders);
        when(orderService.getOrdersByPlayer(blue)).thenReturn(Collections.emptyList());
        when(gameService.getGame(1L)).thenReturn(gameEntity);
        when(territoryService.getTerritoriesByPlayer(red)).thenReturn(territoryEntityList);
        when(territoryService.getTerritoriesByPlayer(blue)).thenReturn(Collections.emptyList());

        service.playOneRound(1L);

        verify(playerService, times(1)).updatePlayerStatus(blue, PlayerStatusEnum.LOSE);
        verify(gameService, times(1)).updateGameRoundAndStatus(gameEntity, GameStatusEnum.PLAYING, 2);
    }

    @Test
    void playOneRoundTest_EndRound() {
        GameEntity gameEntity = getGameEntity();
        gameEntity.setRoundNo(1);


        PlayerEntity red = new PlayerEntity("red");

        gameEntity.getPlayers().add(red);


        List<OrderEntity> orders = new ArrayList<>();
        OrderEntity order = new OrderEntity();
        order.setOrderType(OrderMappingEnum.MOVE);
        orders.add(order);

        List<TerritoryEntity> territoryEntityList = new ArrayList<>();
        TerritoryEntity territoryEntity = new TerritoryEntity();
        territoryEntity.setOwner(red);
        territoryEntityList.add(territoryEntity);

        when(orderService.getOrdersByPlayer(red)).thenReturn(orders);
        when(gameService.getGame(1L)).thenReturn(gameEntity);
        when(territoryService.getTerritoriesByPlayer(red)).thenReturn(territoryEntityList);

        service.playOneRound(1L);

        verify(gameService, times(1)).updateGameRoundAndStatus(gameEntity, GameStatusEnum.ENDED, 2);
    }
}
