package edu.duke.ece651.team13.server.service.order;


import edu.duke.ece651.team13.server.entity.*;
import edu.duke.ece651.team13.server.enums.UnitMappingEnum;
import edu.duke.ece651.team13.server.rulechecker.*;
import edu.duke.ece651.team13.server.service.AttackerService;
import edu.duke.ece651.team13.server.service.PlayerService;
import edu.duke.ece651.team13.server.service.TerritoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static edu.duke.ece651.team13.server.service.TerritoryService.getUnitForType;


@Service
@RequiredArgsConstructor
public class UnitUpgradeOrderService implements OrderFactory {

    @Autowired
    private final TerritoryService territoryService;

    @Autowired
    private final PlayerService playerService;

    /**
     * Get the default rule checker chain
     * UnitUpgradeOwnershipChecker -> UnitUpgradeUnitNumChecker -> UnitUpgradeTechLevelChecker -> UnitUpgradeTechResourceChecker
     */
    private static RuleChecker getDefaultRuleChecker() {
        RuleChecker techResourceChecker = new UnitUpgradeTechResourceChecker(null);
        RuleChecker techLevelChecker = new UnitUpgradeTechLevelChecker(techResourceChecker);
        RuleChecker unitNumChecker = new UnitUpgradeUnitNumChecker(techLevelChecker);
        return new UnitUpgradeOwnershipChecker(unitNumChecker);
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
                UnitUpgradeTechResourceChecker.getTechCost(order));
    }

    private void executeLocally(TerritoryEntity sourceTerritoryEntity,
                                int unitNum,
                                UnitMappingEnum unitType,
                                PlayerEntity player,
                                int techCost) {
        player.setTechResource(player.getTechResource() - techCost);
        if (unitNum > 0) {
            UnitEntity sourceUnit = getUnitForType(sourceTerritoryEntity, unitType);
            sourceUnit.setUnitNum(sourceUnit.getUnitNum() - unitNum);
        }
    }

    @Override
    public void executeOnGame(OrderEntity order, GameEntity game) {
        TerritoryEntity source = game.getMap().getTerritoryEntityById(order.getSource().getId());
        PlayerEntity player = game.getPlayerEntityById(order.getPlayer().getId());
        executeLocally(source,
                order.getUnitNum(),
                order.getUnitType(),
                player,
                UnitUpgradeTechResourceChecker.getTechCost(order));
        territoryService.updateTerritoryUnits(source, source.getUnits());
        playerService.updatePlayerTechResource(player,player.getTechResource());
    }

}
