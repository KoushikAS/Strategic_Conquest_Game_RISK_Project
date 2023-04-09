package edu.duke.ece651.team13.server.service.order;

import edu.duke.ece651.team13.server.entity.*;
import edu.duke.ece651.team13.server.enums.UnitMappingEnum;
import edu.duke.ece651.team13.server.rulechecker.*;
import edu.duke.ece651.team13.server.service.PlayerService;
import edu.duke.ece651.team13.server.service.TerritoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static edu.duke.ece651.team13.server.rulechecker.MoveFoodResourceChecker.getFoodCost;
import static edu.duke.ece651.team13.server.service.TerritoryService.getUnitForType;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MoveOrderService implements OrderFactory {


    @Autowired
    private final TerritoryService territoryService;

    @Autowired
    private final PlayerService playerService;

    /**
     * Get the default rule checker chain
     * MoveOwnershipChecker -> MoveUnitNumChecker -> MoveFoodResourceChecker -> MovePathChecker
     */
    private static RuleChecker getDefaultRuleChecker() {
        RuleChecker pathChecker = new MovePathChecker(null);
        RuleChecker foodResourceChecker = new MoveFoodResourceChecker(pathChecker);
        RuleChecker unitnumChecker = new MoveUnitNumChecker(foodResourceChecker);
        return new MoveOwnershipChecker(unitnumChecker);
    }

    @Override
    public void validateAndExecuteLocally(OrderEntity order, GameEntity game) throws IllegalArgumentException {
        log.info("Locally validating order " + order.getId() + ": " + order.getOrderType().getValue() + " from " +
        order.getSource().getName() + " to " + order.getDestination().getName() + " with " + order.getUnitNum()
                + " units on game " + game.getId());

        RuleChecker ruleChecker = getDefaultRuleChecker();
        PlayerEntity player = game.getPlayerEntityById(order.getPlayer().getId());
        ruleChecker.checkOrder(order, player);
        TerritoryEntity source = game.getMap().getTerritoryEntityById(order.getSource().getId());
        TerritoryEntity destination = game.getMap().getTerritoryEntityById(order.getDestination().getId());
        executeLocally(source, destination, order.getUnitNum(), order.getUnitType(), player, getFoodCost(order));
    }

    private void executeLocally(TerritoryEntity sourceTerritoryEntity, TerritoryEntity destinationTerritoryEntity,
                                int unitNum,
                                UnitMappingEnum unitType,
                                PlayerEntity player,
                                int foodCost) {
        player.setFoodResource(player.getFoodResource() - foodCost);
        UnitEntity sourceUnit = getUnitForType(sourceTerritoryEntity, unitType);
        UnitEntity destUnit = getUnitForType(destinationTerritoryEntity, unitType);
        sourceUnit.setUnitNum(sourceUnit.getUnitNum() - unitNum);
        destUnit.setUnitNum(destUnit.getUnitNum() + unitNum);
    }


    @Override
    public void executeOnGame(OrderEntity order, GameEntity game) {
        log.info("Executing order " + order.getId() + ": " + order.getOrderType().getValue() + " from " +
                order.getSource().getName() + " to " + order.getDestination().getName() + " with " + order.getUnitNum()
                + " units on game " + game.getId());

        TerritoryEntity source = game.getMap().getTerritoryEntityById(order.getSource().getId());
        TerritoryEntity destination = game.getMap().getTerritoryEntityById(order.getDestination().getId());
        PlayerEntity player = game.getPlayerEntityById(order.getPlayer().getId());
        executeLocally(source,
                destination,
                order.getUnitNum(),
                order.getUnitType(),
                player,
                getFoodCost(order));
        territoryService.updateTerritoryUnits(source, source.getUnits());
        territoryService.updateTerritoryUnits(destination, destination.getUnits());
        playerService.updatePlayerFoodResource(player, player.getFoodResource());
    }

}
