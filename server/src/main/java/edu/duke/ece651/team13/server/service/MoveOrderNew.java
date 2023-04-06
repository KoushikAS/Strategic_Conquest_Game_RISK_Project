package edu.duke.ece651.team13.server.service;

import edu.duke.ece651.team13.server.entity.GameEntity;
import edu.duke.ece651.team13.server.entity.OrderEntity;
import edu.duke.ece651.team13.server.entity.TerritoryEntity;
import edu.duke.ece651.team13.server.rulechecker.MoveOwnershipChecker;
import edu.duke.ece651.team13.server.rulechecker.MovePathChecker;
import edu.duke.ece651.team13.server.rulechecker.MoveUnitNumChecker;
import edu.duke.ece651.team13.server.rulechecker.RuleChecker;
import org.springframework.stereotype.Service;

@Service
public class MoveOrderNew implements OrderFactory{


    /**
     * Get the default rule checker chain
     * MoveOwnershipChecker -> MoveUnitNumChecker -> MovePathChecker
     */
    private static RuleChecker getDefaultRuleChecker() {
        RuleChecker pathChecker = new MovePathChecker(null);
        RuleChecker unitnumChecker = new MoveUnitNumChecker(pathChecker);
        return new MoveOwnershipChecker(unitnumChecker);
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
        int destUnitNum = destinationTerritoryEntity.getUnitNum() + unitNo;
        destinationTerritoryEntity.setUnitNum(destUnitNum);
    }

}
