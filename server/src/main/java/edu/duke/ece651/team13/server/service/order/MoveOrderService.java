package edu.duke.ece651.team13.server.service.order;

import edu.duke.ece651.team13.server.entity.GameEntity;
import edu.duke.ece651.team13.server.entity.OrderEntity;
import edu.duke.ece651.team13.server.entity.PlayerEntity;
import edu.duke.ece651.team13.server.entity.TerritoryEntity;
import edu.duke.ece651.team13.server.entity.UnitEntity;
import edu.duke.ece651.team13.server.enums.UnitMappingEnum;
import edu.duke.ece651.team13.server.rulechecker.MoveFoodResourceChecker;
import edu.duke.ece651.team13.server.rulechecker.MoveOwnershipChecker;
import edu.duke.ece651.team13.server.rulechecker.MovePathChecker;
import edu.duke.ece651.team13.server.rulechecker.MoveUnitNumChecker;
import edu.duke.ece651.team13.server.rulechecker.RuleChecker;
import edu.duke.ece651.team13.server.service.TerritoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static edu.duke.ece651.team13.server.rulechecker.MoveFoodResourceChecker.getFoodCost;
import static edu.duke.ece651.team13.server.service.TerritoryService.getUnitForType;

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
        RuleChecker foodResourceChecker = new MoveFoodResourceChecker(pathChecker);
        RuleChecker unitnumChecker = new MoveUnitNumChecker(foodResourceChecker);
        return new MoveOwnershipChecker(unitnumChecker);
    }

    @Override
    public void validateAndExecuteLocally(OrderEntity order, GameEntity game) throws IllegalArgumentException {
        RuleChecker ruleChecker = getDefaultRuleChecker();
        ruleChecker.checkOrder(order);
        TerritoryEntity source = game.getMap().getTerritoryEntityById(order.getSource().getId());
        TerritoryEntity destination = game.getMap().getTerritoryEntityById(order.getDestination().getId());
        executeLocally(source, destination, order.getUnitNum(), order.getUnitType(), order.getPlayer(), getFoodCost(order));
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
        TerritoryEntity source = game.getMap().getTerritoryEntityById(order.getSource().getId());
        TerritoryEntity destination = game.getMap().getTerritoryEntityById(order.getDestination().getId());
        executeLocally(source,
                destination,
                order.getUnitNum(),
                order.getUnitType(),
                order.getPlayer(),
                getFoodCost(order));
        territoryService.updateTerritoryUnits(source, source.getUnits());
        territoryService.updateTerritoryUnits(destination, destination.getUnits());
    }

}
