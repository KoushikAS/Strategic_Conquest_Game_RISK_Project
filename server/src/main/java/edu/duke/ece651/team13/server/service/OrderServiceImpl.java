package edu.duke.ece651.team13.server.service;

import edu.duke.ece651.team13.server.dto.OrderDTO;
import edu.duke.ece651.team13.server.dto.OrdersDTO;
import edu.duke.ece651.team13.server.entity.GameEntity;
import edu.duke.ece651.team13.server.entity.OrderEntity;
import edu.duke.ece651.team13.server.entity.PlayerEntity;
import edu.duke.ece651.team13.server.entity.TerritoryEntity;
import edu.duke.ece651.team13.server.repository.OrderRepository;
import edu.duke.ece651.team13.server.service.order.AttackOrderNew;
import edu.duke.ece651.team13.server.service.order.MoveOrderNew;
import edu.duke.ece651.team13.shared.enums.OrderMappingEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static edu.duke.ece651.team13.shared.enums.OrderMappingEnum.ATTACK;
import static edu.duke.ece651.team13.shared.enums.OrderMappingEnum.MOVE;
import static org.springframework.http.HttpStatus.NOT_FOUND;

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
    private final AttackerService attackerService;

    @Autowired
    private final MoveOrderNew moveOrder;

    @Autowired
    private final AttackOrderNew attackOrder;

    @Override
    public List<OrderEntity> getOrdersByPlayer(PlayerEntity playerEntity) {
        return repository.findByPlayer(playerEntity);
    }

    public List<OrderEntity> getOrderEntityList(OrdersDTO orders, GameEntity game, PlayerEntity player) {
        List<OrderEntity> orderEntities = new ArrayList<>();
        for (OrderDTO orderDTO : orders.getOrders()) {
            Optional<TerritoryEntity> source = game.getMap().getTerritories().stream().filter(territoryEntity -> territoryEntity.getId().equals(orderDTO.getSourceTerritoryId())).findFirst();
            Optional<TerritoryEntity> destination = game.getMap().getTerritories().stream().filter(territoryEntity -> territoryEntity.getId().equals(orderDTO.getDestinationTerritoryId())).findFirst();
            if (!source.isPresent() || !destination.isPresent()) {
                log.error("Either did not find the source territory Id" + orderDTO.getSourceTerritoryId() + " or did not find destination territory Id " + orderDTO.getDestinationTerritoryId());
                throw new ResponseStatusException(NOT_FOUND, "Source or Destination Territory does not exists");
            }

            OrderEntity orderEntity = new OrderEntity();
            orderEntity.setPlayer(player);
            orderEntity.setOrderType(OrderMappingEnum.findByValue(orderDTO.getOrderType()));
            orderEntity.setSource(source.get());
            orderEntity.setDestination(destination.get());
            orderEntity.setUnitNum(orderDTO.getUnits());
            orderEntities.add(orderEntity);
        }
        return orderEntities;
    }


    @Override
    public void validateAndAddOrders(OrdersDTO orders) throws IllegalAccessException {
        PlayerEntity player = playerService.getPlayer(orders.getPlayerId());
        GameEntity game = player.getGame();
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
        for(OrderEntity order: orderEntityList){
            repository.save(order);
        }
    }
}
