package edu.duke.ece651.team13.server.service.order;

import edu.duke.ece651.team13.server.entity.GameEntity;
import edu.duke.ece651.team13.server.entity.OrderEntity;
import edu.duke.ece651.team13.server.entity.PlayerEntity;
import edu.duke.ece651.team13.server.entity.TerritoryEntity;
import edu.duke.ece651.team13.server.entity.UnitEntity;
import edu.duke.ece651.team13.server.rulechecker.MoveFoodResourceChecker;
import edu.duke.ece651.team13.server.rulechecker.MoveOwnershipChecker;
import edu.duke.ece651.team13.server.rulechecker.MovePathChecker;
import edu.duke.ece651.team13.server.rulechecker.MoveUnitNumChecker;
import edu.duke.ece651.team13.server.rulechecker.RuleChecker;
import edu.duke.ece651.team13.server.service.TerritoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static edu.duke.ece651.team13.server.enums.UnitMappingEnum.LEVEL0;
import static edu.duke.ece651.team13.server.rulechecker.MoveFoodResourceChecker.getFoodCost;
import static edu.duke.ece651.team13.server.rulechecker.MoveUnitNumChecker.getUnitEntityHelper;

@Service
@RequiredArgsConstructor
public class MoveOrderNew implements OrderFactory {


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
        executeLocally(game.getMap().getTerritoryEntityById(order.getSource().getId()),
                game.getMap().getTerritoryEntityById(order.getDestination().getId()),
                order.getUnitNum(),
                order.getPlayer(),
                getFoodCost(order));
    }

    private void executeLocally(TerritoryEntity sourceTerritoryEntity,
                                TerritoryEntity destinationTerritoryEntity,
                                int unitNo,
                                PlayerEntity player,
                                int foodCost) {
        player.setFoodResource(player.getFoodResource() - foodCost);
        //TODO Remove the correct vairety of units
        //Temporarily: only operate on basic units
        UnitEntity sourceUnit = getUnitEntityHelper(sourceTerritoryEntity, LEVEL0);
        UnitEntity destUnit = getUnitEntityHelper(destinationTerritoryEntity, LEVEL0);
        sourceUnit.setUnits(sourceUnit.getUnits() - unitNo);
    }


    @Override
    public void executeOnGame(OrderEntity order, GameEntity game) {
        TerritoryEntity sourceTerritoryEntity = game.getMap().getTerritoryEntityById(order.getSource().getId());
        TerritoryEntity destinationTerritoryEntity = game.getMap().getTerritoryEntityById(order.getDestination().getId());
        executeLocally(sourceTerritoryEntity, destinationTerritoryEntity, order.getUnitNum(), order.getPlayer(), getFoodCost(order));
        territoryService.updateTerritoryUnits(sourceTerritoryEntity, sourceTerritoryEntity.getUnits());
        territoryService.updateTerritoryUnits(destinationTerritoryEntity, destinationTerritoryEntity.getUnits());
    }

}
