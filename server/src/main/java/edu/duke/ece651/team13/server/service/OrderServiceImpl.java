package edu.duke.ece651.team13.server.service;

import edu.duke.ece651.team13.server.dto.OrderDTO;
import edu.duke.ece651.team13.server.dto.OrdersDTO;
import edu.duke.ece651.team13.server.entity.GameEntity;
import edu.duke.ece651.team13.server.entity.OrderEntity;
import edu.duke.ece651.team13.server.entity.PlayerEntity;
import edu.duke.ece651.team13.server.entity.TerritoryEntity;
import edu.duke.ece651.team13.server.enums.OrderMappingEnum;
import edu.duke.ece651.team13.server.enums.UnitMappingEnum;
import edu.duke.ece651.team13.server.repository.OrderRepository;
import edu.duke.ece651.team13.server.service.order.AttackOrderService;
import edu.duke.ece651.team13.server.service.order.MoveOrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static edu.duke.ece651.team13.server.enums.GameStatusEnum.ENDED;
import static edu.duke.ece651.team13.server.enums.OrderMappingEnum.ATTACK;
import static edu.duke.ece651.team13.server.enums.OrderMappingEnum.MOVE;
import static edu.duke.ece651.team13.server.enums.PlayerStatusEnum.LOSE;
import static edu.duke.ece651.team13.server.enums.PlayerStatusEnum.PLAYING;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private final OrderRepository repository;

    @Autowired
    private final PlayerService playerService;

    @Autowired
    private final MoveOrderService moveOrder;

    @Autowired
    private final AttackOrderService attackOrder;

    @Autowired
    private final ApplicationEventPublisher eventPublisher;


    @Override
    public List<OrderEntity> getOrdersByPlayer(PlayerEntity playerEntity) {
        return repository.findByPlayer(playerEntity);
    }

    private Boolean isGameReadyForRoundExecution(GameEntity game) {
        //Checking if all the players who are active have submitted orders
        for (PlayerEntity player : game.getPlayers()) {
            if (player.getStatus().equals(PLAYING) && getOrdersByPlayer(player).size() == 0) {
                return false;
            }
        }
        return true;
    }

    private List<OrderEntity> getOrderEntityList(OrdersDTO orders, GameEntity game, PlayerEntity player) {
        List<OrderEntity> orderEntities = new ArrayList<>();
        for (OrderDTO orderDTO : orders.getOrders()) {
            Optional<TerritoryEntity> source = game.getMap().getTerritories().stream().filter(territoryEntity -> territoryEntity.getId().equals(orderDTO.getSourceTerritoryId())).findFirst();
            Optional<TerritoryEntity> destination = game.getMap().getTerritories().stream().filter(territoryEntity -> territoryEntity.getId().equals(orderDTO.getDestinationTerritoryId())).findFirst();
            if (!source.isPresent() || !destination.isPresent()) {
                log.error("Either did not find the source territory Id" + orderDTO.getSourceTerritoryId() + " or did not find destination territory Id " + orderDTO.getDestinationTerritoryId());
                throw new NoSuchElementException("Source or Destination Territory does not exists");
            }

            OrderEntity orderEntity = new OrderEntity();
            orderEntity.setPlayer(player);
            orderEntity.setOrderType(OrderMappingEnum.findByValue(orderDTO.getOrderType()));
            orderEntity.setSource(source.get());
            orderEntity.setDestination(destination.get());
            orderEntity.setUnitNum(orderDTO.getUnitNum());
            orderEntity.setUnitType(UnitMappingEnum.findByValue(orderDTO.getUnitType()));
            orderEntities.add(orderEntity);
        }
        return orderEntities;
    }


    @Override
    public void validateAndAddOrders(OrdersDTO orders) throws IllegalArgumentException {
        PlayerEntity player = playerService.getPlayer(orders.getPlayerId());

        if (player.getStatus().equals(LOSE)) {
            throw new IllegalArgumentException("Player has already lost he cannot issue a order.");
        }

        GameEntity game = player.getGame();

        if (game.getStatus().equals(ENDED)) {
            throw new IllegalArgumentException("Game has already ended no new orders could be issued.");
        }

        if (game.getRoundNo() == 0 && orders
                .getOrders().stream()
                .anyMatch(orderDTO -> !orderDTO.getOrderType().equals(MOVE.getValue()))) {
            throw new IllegalArgumentException("Only Move Orders can be issued during initialising round.");
        }

        List<OrderEntity> orderEntityList = getOrderEntityList(orders, game, player);

        //Validate Move Order
        for (OrderEntity order : orderEntityList) {
            if (order.getOrderType().equals(MOVE)) {
                moveOrder.validateAndExecuteLocally(order, game);
            }
        }

        //Validate Attack order
        for (OrderEntity order : orderEntityList) {
            if (order.getOrderType().equals(ATTACK)) {
                attackOrder.validateAndExecuteLocally(order, game);
            }
        }

        //Save order list
        for (OrderEntity order : orderEntityList) {
            repository.save(order);
        }

        if (isGameReadyForRoundExecution(game)) {
            eventPublisher.publishEvent(game.getId());
        }

    }
}
