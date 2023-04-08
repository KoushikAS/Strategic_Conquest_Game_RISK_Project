package edu.duke.ece651.team13.server.service.order;

import edu.duke.ece651.team13.server.entity.GameEntity;
import edu.duke.ece651.team13.server.entity.OrderEntity;
import edu.duke.ece651.team13.server.entity.TerritoryEntity;
import edu.duke.ece651.team13.server.entity.UnitEntity;
import edu.duke.ece651.team13.server.rulechecker.MoveOwnershipChecker;
import edu.duke.ece651.team13.server.rulechecker.MovePathChecker;
import edu.duke.ece651.team13.server.rulechecker.MoveUnitNumChecker;
import edu.duke.ece651.team13.server.rulechecker.RuleChecker;
import edu.duke.ece651.team13.server.service.TerritoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MoveOrderService implements OrderFactory {


    @Autowired
    private final TerritoryService territoryService;

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
    public void validateAndExecuteLocally(OrderEntity order, GameEntity game) throws IllegalArgumentException {
        RuleChecker ruleChecker = getDefaultRuleChecker();
        ruleChecker.checkOrder(order);
        executelocally(game.getMap().getTerritoryEntityById(order.getSource().getId()), game.getMap().getTerritoryEntityById(order.getDestination().getId()), order.getUnitNum());
    }

    private void executelocally(TerritoryEntity sourceTerritoryEntity, TerritoryEntity destinationTerritoryEntity, int unitNo) {
        for (int i = 0; i < unitNo; i++) {
            //TODO Remove the correct vairety of units
            UnitEntity unit = sourceTerritoryEntity.getUnits().remove(0);
            destinationTerritoryEntity.getUnits().add(unit);
        }
    }

    @Override
    public void executeOnGame(OrderEntity order, GameEntity game) {
        TerritoryEntity sourceTerritoryEntity = game.getMap().getTerritoryEntityById(order.getSource().getId());
        TerritoryEntity destinationTerritoryEntity = game.getMap().getTerritoryEntityById(order.getDestination().getId());
        executelocally(sourceTerritoryEntity, destinationTerritoryEntity, order.getUnitNum());
        territoryService.updateTerritoryUnits(sourceTerritoryEntity, sourceTerritoryEntity.getUnits());
        territoryService.updateTerritoryUnits(destinationTerritoryEntity, destinationTerritoryEntity.getUnits());
    }

}
