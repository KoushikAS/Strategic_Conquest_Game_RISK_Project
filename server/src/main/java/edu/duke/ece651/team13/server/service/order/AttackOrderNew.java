package edu.duke.ece651.team13.server.service.order;

import edu.duke.ece651.team13.server.entity.AttackerEntity;
import edu.duke.ece651.team13.server.entity.GameEntity;
import edu.duke.ece651.team13.server.entity.OrderEntity;
import edu.duke.ece651.team13.server.entity.TerritoryEntity;
import edu.duke.ece651.team13.server.enums.UnitMappingEnum;
import edu.duke.ece651.team13.server.rulechecker.AttackOwnershipChecker;
import edu.duke.ece651.team13.server.rulechecker.AttackPathChecker;
import edu.duke.ece651.team13.server.rulechecker.AttackUnitNumChecker;
import edu.duke.ece651.team13.server.rulechecker.RuleChecker;
import edu.duke.ece651.team13.server.service.AttackerService;
import edu.duke.ece651.team13.server.service.TerritoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AttackOrderNew implements OrderFactory {

    @Autowired
    private final TerritoryService territoryService;

    @Autowired
    private final AttackerService attackerService;

    /**
     * Get the default rule checker chain
     * AttackOwnershipChecker -> AttackUnitNumChecker -> AttackPathChecker
     */
    private static RuleChecker getDefaultRuleChecker() {
        RuleChecker pathChecker = new AttackPathChecker(null);
        RuleChecker unitnumChecker = new AttackUnitNumChecker(pathChecker);
        return new AttackOwnershipChecker(unitnumChecker);
    }

    @Override
    public void validateAndExecuteLocally(OrderEntity order, GameEntity game) throws IllegalAccessException {
        RuleChecker ruleChecker = getDefaultRuleChecker();
        ruleChecker.checkOrder(order);
        executeLocaly(game.getMap().getTerritoryEntityById(order.getSource().getId()), order.getUnitNum());
    }

    private void executeLocaly(TerritoryEntity sourceTerritoryEntity, int unitNo) {
        //TODO remove correct unit type
        if (unitNo > 0) {
            sourceTerritoryEntity.getUnits().subList(0, unitNo).clear();
        }
    }

    @Override
    public void executeOnGame(OrderEntity order, GameEntity game) {
        TerritoryEntity sourceTerritoryEntity = game.getMap().getTerritoryEntityById(order.getSource().getId());
        executeLocaly(sourceTerritoryEntity, order.getUnitNum());
        territoryService.updateTerritoryUnits(sourceTerritoryEntity, sourceTerritoryEntity.getUnits());
        attackerService.addAttacker(order.getDestination(), order.getPlayer(), UnitMappingEnum.LEVEL0, order.getUnitNum());
    }

}
