package edu.duke.ece651.team13.server.service.order;

import edu.duke.ece651.team13.server.entity.GameEntity;
import edu.duke.ece651.team13.server.entity.OrderEntity;
import edu.duke.ece651.team13.server.entity.PlayerEntity;
import edu.duke.ece651.team13.server.entity.TerritoryEntity;
import edu.duke.ece651.team13.server.entity.UnitEntity;
import edu.duke.ece651.team13.server.enums.UnitMappingEnum;
import edu.duke.ece651.team13.server.rulechecker.AttackFoodResourceChecker;
import edu.duke.ece651.team13.server.rulechecker.AttackOwnershipChecker;
import edu.duke.ece651.team13.server.rulechecker.AttackPathChecker;
import edu.duke.ece651.team13.server.rulechecker.AttackUnitNumChecker;
import edu.duke.ece651.team13.server.rulechecker.MoveFoodResourceChecker;
import edu.duke.ece651.team13.server.rulechecker.RuleChecker;
import edu.duke.ece651.team13.server.service.AttackerService;
import edu.duke.ece651.team13.server.service.TerritoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static edu.duke.ece651.team13.server.service.TerritoryService.getUnitForType;

@Service
@RequiredArgsConstructor
public class AttackOrderService implements OrderFactory {

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
        TerritoryEntity source = game.getMap().getTerritoryEntityById(order.getSource().getId());
        executeLocally(source,
                order.getUnitNum(),
                order.getUnitType(),
                order.getPlayer(),
                MoveFoodResourceChecker.getFoodCost(order));
    }

    private void executeLocally(TerritoryEntity sourceTerritoryEntity,
                                int unitNum,
                                UnitMappingEnum unitType,
                                PlayerEntity player,
                                int foodCost) {
        player.setFoodResource(player.getFoodResource() - foodCost);
        if (unitNum > 0) {
            UnitEntity sourceUnit = getUnitForType(sourceTerritoryEntity, unitType);
            sourceUnit.setUnitNum(sourceUnit.getUnitNum() - unitNum);
        }
    }

    @Override
    public void executeOnGame(OrderEntity order, GameEntity game) {
        TerritoryEntity source = game.getMap().getTerritoryEntityById(order.getSource().getId());
        executeLocally(source,
                order.getUnitNum(),
                order.getUnitType(),
                order.getPlayer(),
                MoveFoodResourceChecker.getFoodCost(order));
        territoryService.updateTerritoryUnits(source, source.getUnits());
        attackerService.addAttacker(order.getDestination(),
                order.getPlayer(),
                order.getUnitType(),
                order.getUnitNum());
    }

}
