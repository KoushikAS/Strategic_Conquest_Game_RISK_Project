package edu.duke.ece651.team13.server.service;

import edu.duke.ece651.team13.server.entity.GameEntity;
import edu.duke.ece651.team13.server.entity.OrderEntity;
import edu.duke.ece651.team13.server.entity.PlayerEntity;
import edu.duke.ece651.team13.server.entity.TerritoryEntity;
import edu.duke.ece651.team13.server.service.order.AttackOrderNew;
import edu.duke.ece651.team13.server.service.order.MoveOrderNew;
import edu.duke.ece651.team13.shared.enums.OrderMappingEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoundServiceImpl implements RoundService {

    @Autowired
    private final OrderService orderService;

    @Autowired
    private final MoveOrderNew moveOrder;

    @Autowired
    private final AttackOrderNew attackOrder;

    @Autowired
    private final CombatResolutionService combatResolutionService;

    @Autowired
    private final TerritoryService territoryService;


    @Override
    public Boolean isGameReadyForRoundExecution(GameEntity game) {
        //Checking if all the players have submitted orders
        for (PlayerEntity player : game.getPlayers()) {
            if (orderService.getOrdersByPlayer(player).size() == 0) {
                return false;
            }
        }
        return true;
    }

    private void executePlayersOrders(GameEntity game) {
        for (PlayerEntity player : game.getPlayers()) {
            List<OrderEntity> orders = orderService.getOrdersByPlayer(player);

            orders.stream()
                    .filter(order -> order.getOrderType().equals(OrderMappingEnum.MOVE))
                    .forEach(order -> moveOrder.executeOnGame(order, game));

            orders.stream()
                    .filter(order -> order.getOrderType().equals(OrderMappingEnum.ATTACK))
                    .forEach(order -> attackOrder.executeOnGame(order, game));
        }
    }

    private void resolveCombatForGame(GameEntity game) {
        game.getMap().getTerritories().forEach(combatResolutionService::resolveCombot);
    }

    private void updateResourceForPlayers(List<PlayerEntity> players){
        for(PlayerEntity player: players){
            List<TerritoryEntity> territoryEntities = territoryService.getTerritoriesByPlayer(player);
            int foodProduction = 0;
            int techProduction = 0;
            for(TerritoryEntity territory: territoryEntities){
                foodProduction += territory.getFoodProduction();
                techProduction += territory.getTechProduction();
            }
            player.setFoodResource(player.getFoodResource()+foodProduction);
            player.setTechResource(player.getTechResource()+techProduction);
        }
    }

    @Override
    public void playOneRound(GameEntity game) {
        executePlayersOrders(game);
        resolveCombatForGame(game);
        updateResourceForPlayers(game.getPlayers());
    }
}
