package edu.duke.ece651.team13.server.service;

import edu.duke.ece651.team13.server.entity.GameEntity;
import edu.duke.ece651.team13.server.entity.OrderEntity;
import edu.duke.ece651.team13.server.entity.PlayerEntity;
import edu.duke.ece651.team13.server.entity.TerritoryEntity;
import edu.duke.ece651.team13.server.enums.GameStatusEnum;
import edu.duke.ece651.team13.server.enums.OrderMappingEnum;
import edu.duke.ece651.team13.server.enums.PlayerStatusEnum;
import edu.duke.ece651.team13.server.service.order.AttackOrderService;
import edu.duke.ece651.team13.server.service.order.MoveOrderService;
import edu.duke.ece651.team13.server.service.order.UnitUpgradeOrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

import static edu.duke.ece651.team13.server.enums.PlayerStatusEnum.PLAYING;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoundServiceImpl implements RoundService {

    @Autowired
    private final OrderService orderService;

    @Autowired
    private final MoveOrderService moveOrder;

    @Autowired
    private final AttackOrderService attackOrder;

    @Autowired
    private final UnitUpgradeOrderService unitUpgradeOrder;

    @Autowired
    private final CombatResolutionService combatResolutionService;

    @Autowired
    private final TerritoryService territoryService;

    @Autowired
    private final PlayerService playerService;

    @Autowired
    private final GameService gameService;


    private void executePlayersOrders(GameEntity game) {
        for (PlayerEntity player : game.getPlayers()) {
            List<OrderEntity> orders = orderService.getOrdersByPlayer(player);

            orders.stream()
                    .filter(order -> order.getOrderType().equals(OrderMappingEnum.MOVE))
                    .forEach(order -> moveOrder.executeOnGame(order, game));

            orders.stream()
                    .filter(order -> order.getOrderType().equals(OrderMappingEnum.ATTACK))
                    .forEach(order -> attackOrder.executeOnGame(order, game));

            orders.stream()
                    .filter(order -> order.getOrderType().equals(OrderMappingEnum.UNIT_UPGRADE))
                    .forEach(order -> unitUpgradeOrder.executeOnGame(order, game));
        }
    }

    private void resolveCombatForGame(GameEntity game) {
        game.getMap().getTerritories().forEach(combatResolutionService::resolveCombot);
    }

    private void updatePlayerStatus(GameEntity game) {
        for (PlayerEntity player : game.getPlayers()) {
            if (territoryService.getTerritoriesByPlayer(player).isEmpty()) {
                playerService.updatePlayerStatus(player, PlayerStatusEnum.LOSE);
            }
        }
    }

    private void updateGameStatus(GameEntity game) {
        long noOfPlayersPlaying = game.getPlayers().stream().filter(player -> player.getStatus().equals(PLAYING)).count();
        if (noOfPlayersPlaying <= 1) {
            gameService.updateGameRoundAndStatus(game, GameStatusEnum.ENDED, game.getRoundNo() + 1);
        } else {
            gameService.updateGameRoundAndStatus(game, GameStatusEnum.PLAYING, game.getRoundNo() + 1);
        }
    }

    private void updateResourceForPlayers(List<PlayerEntity> players) {
        for (PlayerEntity player : players) {
            List<TerritoryEntity> territoryEntities = territoryService.getTerritoriesByPlayer(player);
            int foodProduction = 0;
            int techProduction = 0;
            for (TerritoryEntity territory : territoryEntities) {
                foodProduction += territory.getFoodProduction();
                techProduction += territory.getTechProduction();
            }
            playerService.updatePlayerFoodResource(player, player.getFoodResource() + foodProduction);
            playerService.updatePlayerTechResource(player, player.getTechResource() + techProduction);
        }
    }

    private void clearOrders(GameEntity game){
        for(PlayerEntity player:game.getPlayers()){
            orderService.deleteOrdersByPlayer(player);
        }
    }

    @Override
    @Async
    @EventListener
    public void playOneRound(Long gameId) {
        log.info("Executing Round for Game Id :" + gameId);
        GameEntity game = gameService.getGame(gameId);
        executePlayersOrders(game);

        if (game.getRoundNo() >= 1) {
            resolveCombatForGame(game);
            //TODO Update Resources  like Units, Technology and Food
            updateResourceForPlayers(game.getPlayers());
            updatePlayerStatus(game);
        }

        clearOrders(game);
        updateGameStatus(game);
    }
}