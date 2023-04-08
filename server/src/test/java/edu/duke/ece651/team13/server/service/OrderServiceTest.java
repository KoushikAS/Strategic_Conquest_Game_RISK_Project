package edu.duke.ece651.team13.server.service;

import edu.duke.ece651.team13.server.dto.OrderDTO;
import edu.duke.ece651.team13.server.dto.OrdersDTO;
import edu.duke.ece651.team13.server.entity.*;
import edu.duke.ece651.team13.server.enums.GameStatusEnum;
import edu.duke.ece651.team13.server.repository.OrderRepository;
import edu.duke.ece651.team13.server.service.order.AttackOrderService;
import edu.duke.ece651.team13.server.service.order.MoveOrderService;
import edu.duke.ece651.team13.server.enums.PlayerStatusEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import static edu.duke.ece651.team13.server.enums.OrderMappingEnum.ATTACK;
import static edu.duke.ece651.team13.server.enums.OrderMappingEnum.MOVE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    private OrderService service; //service under test

    @Mock
    private OrderRepository repository;

    @Mock
    private PlayerService playerService;

    @Mock
    private MoveOrderService moveOrder;

    @Mock
    private AttackOrderService attackOrder;

    @Mock
    private ApplicationEventPublisher eventPublisher;

    @Mock
    private RoundService roundService;


    @BeforeEach
    void setUp() {
        service = new OrderServiceImpl(repository, playerService, moveOrder, attackOrder, eventPublisher);
    }

    @Test
    void getOrdersByPlayerTest() {
        List<OrderEntity> orders = new ArrayList<>();
        orders.add(new OrderEntity());
        when(repository.findByPlayer(any())).thenReturn(orders);
        List<OrderEntity> actual = service.getOrdersByPlayer(new PlayerEntity("Red"));
        assertEquals(orders, actual);
    }

    @Test
    void validateAndAddOrders_LoosingPlayerIssueOrderTest() {
        PlayerEntity losePlayer = new PlayerEntity();
        losePlayer.setStatus(PlayerStatusEnum.LOSE);
        losePlayer.setId(1L);
        when(playerService.getPlayer(any())).thenReturn(losePlayer);

        List<OrderDTO> orderDTOS = new ArrayList<>();
        orderDTOS.add(new OrderDTO(1L, 2L, 5, MOVE.getValue()));
        OrdersDTO ordersDTO = new OrdersDTO();
        ordersDTO.setOrders(orderDTOS);
        ordersDTO.setPlayerId(losePlayer.getId());

        assertThrows(IllegalArgumentException.class, () -> service.validateAndAddOrders(ordersDTO));
    }

    @Test
    void validateAndAddOrders_GameEndedIssueOrderTest() {
        GameEntity game = new GameEntity();
        game.setStatus(GameStatusEnum.ENDED);
        PlayerEntity player = new PlayerEntity();
        player.setStatus(PlayerStatusEnum.PLAYING);
        player.setId(1L);
        player.setGame(game);
        when(playerService.getPlayer(any())).thenReturn(player);

        List<OrderDTO> orderDTOS = new ArrayList<>();
        orderDTOS.add(new OrderDTO(1L, 2L, 5, MOVE.getValue()));
        OrdersDTO ordersDTO = new OrdersDTO();
        ordersDTO.setOrders(orderDTOS);
        ordersDTO.setPlayerId(player.getId());

        assertThrows(IllegalArgumentException.class, () -> service.validateAndAddOrders(ordersDTO));
    }

    @Test
    void validateAndAddOrders_OtherOrdersDuringInitaliseRoundTest() {
        GameEntity game = new GameEntity();
        game.setStatus(GameStatusEnum.PLAYING);
        game.setRoundNo(0);
        PlayerEntity player = new PlayerEntity();
        player.setStatus(PlayerStatusEnum.PLAYING);
        player.setId(1L);
        player.setGame(game);
        when(playerService.getPlayer(any())).thenReturn(player);

        List<OrderDTO> orderDTOS = new ArrayList<>();
        orderDTOS.add(new OrderDTO(1L, 1L, 5, MOVE.getValue()));
        orderDTOS.add(new OrderDTO(1L, 1L, 5, ATTACK.getValue()));
        OrdersDTO ordersDTO = new OrdersDTO();
        ordersDTO.setOrders(orderDTOS);
        ordersDTO.setPlayerId(player.getId());

        assertThrows(IllegalArgumentException.class, () -> service.validateAndAddOrders(ordersDTO));
    }

    @Test
    void validateAndAddOrders_OrdersContainInvalidTerritoryTest() throws IllegalAccessException {
        TerritoryEntity territory = new TerritoryEntity();
        territory.setId(1L);
        MapEntity map = new MapEntity();
        map.getTerritories().add(territory);
        GameEntity game = new GameEntity();
        game.setMap(map);
        game.setStatus(GameStatusEnum.PLAYING);
        game.setRoundNo(1);
        PlayerEntity player = new PlayerEntity();
        player.setStatus(PlayerStatusEnum.PLAYING);
        player.setId(1L);
        player.setGame(game);
        when(playerService.getPlayer(any())).thenReturn(player);

        List<OrderDTO> orderDTOS = new ArrayList<>();
        orderDTOS.add(new OrderDTO(5L, 1L, 5, MOVE.getValue()));
        OrdersDTO ordersDTO = new OrdersDTO();
        ordersDTO.setOrders(orderDTOS);
        ordersDTO.setPlayerId(player.getId());

        assertThrows(NoSuchElementException.class, () -> service.validateAndAddOrders(ordersDTO));
    }

    @Test
    void validateAndAddOrders_OrdersContainInvalidDestinationTerritoryTest() throws IllegalAccessException {
        TerritoryEntity territory = new TerritoryEntity();
        territory.setId(1L);
        MapEntity map = new MapEntity();
        map.getTerritories().add(territory);
        GameEntity game = new GameEntity();
        game.setMap(map);
        game.setStatus(GameStatusEnum.PLAYING);
        game.setRoundNo(1);
        PlayerEntity player = new PlayerEntity();
        player.setStatus(PlayerStatusEnum.PLAYING);
        player.setId(1L);
        player.setGame(game);
        when(playerService.getPlayer(any())).thenReturn(player);

        List<OrderDTO> orderDTOS = new ArrayList<>();
        orderDTOS.add(new OrderDTO(1L, 5L, 5, MOVE.getValue()));
        OrdersDTO ordersDTO = new OrdersDTO();
        ordersDTO.setOrders(orderDTOS);
        ordersDTO.setPlayerId(player.getId());

        assertThrows(NoSuchElementException.class, () -> service.validateAndAddOrders(ordersDTO));
    }

    @Test
    void validateAndAddOrders_OrdersContainInvalidOrderTypeTest() throws IllegalAccessException {
        TerritoryEntity territory = new TerritoryEntity();
        territory.setId(1L);
        MapEntity map = new MapEntity();
        map.getTerritories().add(territory);
        GameEntity game = new GameEntity();
        game.setMap(map);
        game.setStatus(GameStatusEnum.PLAYING);
        game.setRoundNo(1);
        PlayerEntity player = new PlayerEntity();
        player.setStatus(PlayerStatusEnum.PLAYING);
        player.setId(1L);
        player.setGame(game);
        when(playerService.getPlayer(any())).thenReturn(player);

        List<OrderDTO> orderDTOS = new ArrayList<>();
        orderDTOS.add(new OrderDTO(1L, 1L, 5, "Invalid"));
        OrdersDTO ordersDTO = new OrdersDTO();
        ordersDTO.setOrders(orderDTOS);
        ordersDTO.setPlayerId(player.getId());

        assertThrows(IllegalArgumentException.class, () -> service.validateAndAddOrders(ordersDTO));
    }

    @Test
    void validateAndAddOrders_SuccessButNoReadyForRoundExecutionTest() throws IllegalAccessException {
        TerritoryEntity territory = new TerritoryEntity();
        territory.setId(1L);
        MapEntity map = new MapEntity();
        map.getTerritories().add(territory);
        GameEntity game = new GameEntity();
        game.setMap(map);
        game.setStatus(GameStatusEnum.PLAYING);
        game.setRoundNo(1);
        PlayerEntity player = new PlayerEntity();
        player.setStatus(PlayerStatusEnum.PLAYING);
        player.setId(1L);
        player.setGame(game);
        game.getPlayers().add(player);


        when(playerService.getPlayer(any())).thenReturn(player);
        when(repository.findByPlayer(player)).thenReturn(Collections.emptyList());

        List<OrderDTO> orderDTOS = new ArrayList<>();
        orderDTOS.add(new OrderDTO(1L, 1L, 5, MOVE.getValue()));
        orderDTOS.add(new OrderDTO(1L, 1L, 5, ATTACK.getValue()));
        OrdersDTO ordersDTO = new OrdersDTO();
        ordersDTO.setOrders(orderDTOS);
        ordersDTO.setPlayerId(player.getId());

        service.validateAndAddOrders(ordersDTO);
        verify(repository, times(2)).save(any(OrderEntity.class));
        verify(eventPublisher, times(0)).publishEvent(any(Long.class));
    }

    @Test
    void validateAndAddOrders_SuccessAndReadyForRoundExecutionTest() throws IllegalAccessException {
        TerritoryEntity territory = new TerritoryEntity();
        territory.setId(1L);
        MapEntity map = new MapEntity();
        map.getTerritories().add(territory);
        GameEntity game = new GameEntity();
        game.setMap(map);
        game.setStatus(GameStatusEnum.PLAYING);
        game.setRoundNo(1);
        game.setId(1L);

        PlayerEntity player = new PlayerEntity();
        player.setStatus(PlayerStatusEnum.PLAYING);
        player.setId(1L);
        player.setGame(game);
        game.getPlayers().add(player);

        PlayerEntity loosePlayer = new PlayerEntity();
        loosePlayer.setStatus(PlayerStatusEnum.LOSE);
        loosePlayer.setId(2L);
        loosePlayer.setGame(game);
        game.getPlayers().add(loosePlayer);

        List<OrderEntity> orderEntities = new ArrayList<>();
        orderEntities.add(new OrderEntity());
        when(playerService.getPlayer(any())).thenReturn(player);
        when(repository.findByPlayer(player)).thenReturn(orderEntities);

        List<OrderDTO> orderDTOS = new ArrayList<>();
        orderDTOS.add(new OrderDTO(1L, 1L, 5, MOVE.getValue()));
        orderDTOS.add(new OrderDTO(1L, 1L, 5, ATTACK.getValue()));
        OrdersDTO ordersDTO = new OrdersDTO();
        ordersDTO.setOrders(orderDTOS);
        ordersDTO.setPlayerId(player.getId());

        service.validateAndAddOrders(ordersDTO);
        verify(repository, times(2)).save(any(OrderEntity.class));
        verify(eventPublisher, times(1)).publishEvent(any(Long.class));

    }
}