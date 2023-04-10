package edu.duke.ece651.team13.server.service.order;


import edu.duke.ece651.team13.server.entity.*;
import edu.duke.ece651.team13.server.rulechecker.*;
import edu.duke.ece651.team13.server.service.AttackerService;
import edu.duke.ece651.team13.server.service.PlayerService;
import edu.duke.ece651.team13.server.service.UnitService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static edu.duke.ece651.team13.server.service.TerritoryService.getUnitForType;

import java.util.NoSuchElementException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class AttackOrderService implements OrderFactory {

    @Autowired
    private final UnitService unitService;

    @Autowired
    private final AttackerService attackerService;

    @Autowired
    private final PlayerService playerService;

    /**
     * Get the default rule checker chain
     * AttackOwnershipChecker -> AttackUnitNumChecker -> AttackFoodResourceChecker -> AttackPathChecker
     */
    private static RuleChecker getDefaultRuleChecker() {
        RuleChecker pathChecker = new AttackPathChecker(null);
        RuleChecker foodResourceChecker = new AttackFoodResourceChecker(pathChecker);
        RuleChecker unitNumChecker = new AttackUnitNumChecker(foodResourceChecker);
        return new AttackOwnershipChecker(unitNumChecker);
    }

    @Override
    public void validateAndExecuteLocally(OrderEntity order, GameEntity game) throws IllegalArgumentException {
        RuleChecker ruleChecker = getDefaultRuleChecker();
        PlayerEntity player = game.getPlayerEntityById(order.getPlayer().getId());
        ruleChecker.checkOrder(order, player);
        
        TerritoryEntity source = game.getMap().getTerritoryEntityById(order.getSource().getId());
        UnitEntity sourceUnit = getUnitForType(source, order.getUnitType());
        executeLocally(sourceUnit, order.getUnitNum(), player, AttackFoodResourceChecker.getFoodCost(order));
    }

    private void executeLocally(UnitEntity sourceUnit, int unitNum, PlayerEntity player, int foodCost) {
        player.setFoodResource(player.getFoodResource() - foodCost);
        if (unitNum > 0) {
            sourceUnit.setUnitNum(sourceUnit.getUnitNum() - unitNum);
        }
    }

    @Override
    public void executeOnGame(OrderEntity order, GameEntity game) {
        TerritoryEntity source = game.getMap().getTerritoryEntityById(order.getSource().getId());
        PlayerEntity player = game.getPlayerEntityById(order.getPlayer().getId());
        UnitEntity sourceUnit = getUnitForType(source, order.getUnitType());
        executeLocally(sourceUnit, order.getUnitNum(), player, MoveFoodResourceChecker.getFoodCost(order));
        unitService.updateUnit(sourceUnit, sourceUnit.getUnitNum());
        attackerService.addAttacker(order.getDestination(), player, order.getUnitType(), order.getUnitNum());
        playerService.updatePlayerFoodResource(player, player.getFoodResource());
    }

}
