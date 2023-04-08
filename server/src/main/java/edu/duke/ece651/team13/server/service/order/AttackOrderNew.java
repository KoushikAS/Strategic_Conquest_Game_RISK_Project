package edu.duke.ece651.team13.server.service.order;

import edu.duke.ece651.team13.server.entity.*;
import edu.duke.ece651.team13.server.enums.UnitMappingEnum;
import edu.duke.ece651.team13.server.rulechecker.*;
import edu.duke.ece651.team13.server.service.AttackerService;
import edu.duke.ece651.team13.server.service.TerritoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static edu.duke.ece651.team13.server.rulechecker.AttackFoodResourceChecker.getFoodCost;

@Service
@RequiredArgsConstructor
public class AttackOrderNew implements OrderFactory {

    @Autowired
    private final TerritoryService territoryService;

    @Autowired
    private final AttackerService attackerService;

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
        ruleChecker.checkOrder(order);
        executeLocally(game.getMap().getTerritoryEntityById(order.getSource().getId()), order.getUnitNum(), order.getPlayer(), getFoodCost(order));
    }

    private void executeLocally(TerritoryEntity sourceTerritoryEntity, int unitNo, PlayerEntity player, int foodCost) {
        player.setFoodResource(player.getFoodResource()-foodCost);
        //TODO remove correct unit type
        if (unitNo > 0) {
            sourceTerritoryEntity.getUnits().subList(0, unitNo).clear();
        }
    }

    @Override
    public void executeOnGame(OrderEntity order, GameEntity game) {
        TerritoryEntity sourceTerritoryEntity = game.getMap().getTerritoryEntityById(order.getSource().getId());
        executeLocally(sourceTerritoryEntity, order.getUnitNum(), order.getPlayer(), getFoodCost(order));
        territoryService.updateTerritoryUnits(sourceTerritoryEntity, sourceTerritoryEntity.getUnits());
        attackerService.addAttacker(order.getDestination(), order.getPlayer(), UnitMappingEnum.LEVEL0, order.getUnitNum());
    }

}
