package edu.duke.ece651.team13.server.service.order;

import edu.duke.ece651.team13.server.entity.*;
import edu.duke.ece651.team13.server.rulechecker.*;
import edu.duke.ece651.team13.server.service.PlayerService;
import edu.duke.ece651.team13.server.service.UnitService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static edu.duke.ece651.team13.server.rulechecker.MoveFoodResourceChecker.getFoodCost;

/**
 * Move order
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MoveOrderService implements OrderFactory {

    @Autowired
    private final UnitService unitService;

    @Autowired
    private final PlayerService playerService;

    /**
     * Get the default rule checker chain
     * MoveOwnershipChecker -> MoveUnitNumChecker -> MoveFoodResourceChecker -> MovePathChecker
     */
    private static RuleChecker getDefaultRuleChecker() {
        RuleChecker foodResourceChecker = new MoveFoodResourceChecker(null);
        RuleChecker pathChecker = new MovePathChecker(foodResourceChecker);
        RuleChecker unitnumChecker = new MoveUnitNumChecker(pathChecker);
        return new MoveOwnershipChecker(unitnumChecker);
    }

    /**
     * Validates and executes an order locally within the game.
     */
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
        UnitEntity sourceUnit = source.getUnitForType( order.getUnitType());
        UnitEntity destUnit = destination.getUnitForType( order.getUnitType());
        executeLocally(sourceUnit, destUnit, order.getUnitNum(), player, getFoodCost(order));
    }

    /**
     * Executes the given order on the game locally, updating the necessary game entities.
     */
    private void executeLocally(UnitEntity sourceUnit, UnitEntity destUnit, int unitNum, PlayerEntity player, int foodCost) {
        player.setFoodResource(player.getFoodResource() - foodCost);
        sourceUnit.setUnitNum(sourceUnit.getUnitNum() - unitNum);
        destUnit.setUnitNum(destUnit.getUnitNum() + unitNum);
    }

    /**
     * Executes an order on the game entity and save to database
     */
    @Override
    public void executeOnGame(OrderEntity order, GameEntity game) {
        log.info("Executing order " + order.getId() + ": " + order.getOrderType().getValue() + " from " +
                order.getSource().getName() + " to " + order.getDestination().getName() + " with " + order.getUnitNum()
                + " units on game " + game.getId());

        TerritoryEntity source = game.getMap().getTerritoryEntityById(order.getSource().getId());
        TerritoryEntity destination = game.getMap().getTerritoryEntityById(order.getDestination().getId());
        PlayerEntity player = game.getPlayerEntityById(order.getPlayer().getId());
        UnitEntity sourceUnit = source.getUnitForType( order.getUnitType());
        UnitEntity destUnit = destination.getUnitForType( order.getUnitType());

        executeLocally(sourceUnit, destUnit, order.getUnitNum(), player, getFoodCost(order));

        unitService.updateUnit(sourceUnit, sourceUnit.getUnitNum());
        unitService.updateUnit(destUnit, destUnit.getUnitNum());
        playerService.updatePlayerFoodResource(player, player.getFoodResource());
    }

}
