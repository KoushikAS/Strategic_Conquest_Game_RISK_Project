package edu.duke.ece651.team13.server.service.order;

import edu.duke.ece651.team13.server.entity.GameEntity;
import edu.duke.ece651.team13.server.entity.OrderEntity;
import edu.duke.ece651.team13.server.entity.TerritoryEntity;
import edu.duke.ece651.team13.server.rulechecker.AttackOwnershipChecker;
import edu.duke.ece651.team13.server.rulechecker.AttackPathChecker;
import edu.duke.ece651.team13.server.rulechecker.AttackUnitNumChecker;
import edu.duke.ece651.team13.server.rulechecker.RuleChecker;
import org.springframework.stereotype.Service;

@Service
public class AttackOrderNew implements OrderFactory{


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
        executeLocally(game.getMap().getTerritoryEntityById(order.getSource().getId()), game.getMap().getTerritoryEntityById(order.getDestination().getId()), order.getUnitNum());
    }

    public void executeLocally(TerritoryEntity sourceTerritoryEntity, TerritoryEntity destinationTerritoryEntity , int unitNo ) {
        int sourceUnitNum = sourceTerritoryEntity.getUnitNum() - unitNo;
        sourceTerritoryEntity.setUnitNum(sourceUnitNum);
    }

}
