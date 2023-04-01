package edu.duke.ece651.team13.server.service;

import edu.duke.ece651.team13.server.dto.OrderDTO;
import edu.duke.ece651.team13.server.dto.OrdersDTO;
import edu.duke.ece651.team13.server.entity.*;
import edu.duke.ece651.team13.server.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

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

    @Override
    public List<OrderEntity> getOrdersByPlayer(PlayerEntity playerEntity) {
        return repository.findByPlayer(playerEntity);
    }

    public void addOrders(OrdersDTO orders, GameEntity game, PlayerEntity player){
        for(OrderDTO orderDTO : orders.getOrders()) {
            Optional<TerritoryEntity> source = game.getMap().getTerritories().stream().filter(territoryEntity -> territoryEntity.getId().equals(orderDTO.getSourceTerritoryId())).findFirst();
            Optional<TerritoryEntity> destination = game.getMap().getTerritories().stream().filter(territoryEntity -> territoryEntity.getId().equals(orderDTO.getDestinationTerritoryId())).findFirst();
            if(!source.isPresent() || !destination.isPresent()){
                log.error("Either did not find the source territory Id" + orderDTO.getSourceTerritoryId() + " or did not find destination territory Id "+ orderDTO.getDestinationTerritoryId());
                throw new ResponseStatusException(NOT_FOUND, "Source or Destination Territory does not exists");
            }

            OrderEntity orderEntity = new OrderEntity();
            orderEntity.setPlayer(player);
            orderEntity.setOrderType(MOVE);
            orderEntity.setSource(source.get());
            orderEntity.setDestination(destination.get());
            orderEntity.setUnitNum(orderDTO.getUnits());
            repository.save(orderEntity);
        }
    }

    @Override
    public void validateAndAddOrders(OrdersDTO orders){
        PlayerEntity player = playerService.getPlayer(orders.getPlayerId());
        GameEntity game = player.getGame();
        //TODO validate orders

        addOrders(orders, game, player);
    }
}
