package edu.duke.ece651.team13.server.service.order;


import edu.duke.ece651.team13.server.entity.*;
import edu.duke.ece651.team13.server.rulechecker.*;
import edu.duke.ece651.team13.server.service.PlayerService;
import edu.duke.ece651.team13.server.service.UnitService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static edu.duke.ece651.team13.server.enums.UnitMappingEnum.getNextLevel;
import static edu.duke.ece651.team13.server.service.TerritoryService.getUnitForType;


@Service
@RequiredArgsConstructor
public class UnitUpgradeOrderService implements OrderFactory {

    @Autowired
    private final UnitService unitService;

    @Autowired
    private final PlayerService playerService;

    /**
     * Get the default rule checker chain
     * UnitUpgradeOwnershipChecker -> UnitUpgradeUnitNumChecker -> UnitUpgradeTechLevelChecker -> UnitUpgradeTechResourceChecker -> UnitUpgradeMaxLevelChecker
     */
    private static RuleChecker getDefaultRuleChecker() {
        RuleChecker maxLevelChecker = new UnitUpgradeMaxLevelChecker(null);
        RuleChecker techResourceChecker = new UnitUpgradeTechResourceChecker(maxLevelChecker);
        RuleChecker techLevelChecker = new UnitUpgradeTechLevelChecker(techResourceChecker);
        RuleChecker unitNumChecker = new UnitUpgradeUnitNumChecker(techLevelChecker);
        return new UnitUpgradeOwnershipChecker(unitNumChecker);
    }

    @Override
    public void validateAndExecuteLocally(OrderEntity order, GameEntity game) throws IllegalArgumentException {
        RuleChecker ruleChecker = getDefaultRuleChecker();
        PlayerEntity player = game.getPlayerEntityById(order.getPlayer().getId());
        ruleChecker.checkOrder(order, player);
        TerritoryEntity source = game.getMap().getTerritoryEntityById(order.getSource().getId());
        UnitEntity currentUnit = getUnitForType(source, order.getUnitType());
        UnitEntity upgradedUnit = getUnitForType(source, getNextLevel(order.getUnitType()));

        executeLocally(currentUnit, upgradedUnit, order.getUnitNum(), player, UnitUpgradeTechResourceChecker.getTechCost(order));
    }

    private void executeLocally(UnitEntity currentUnit, UnitEntity upgradedUnit, int unitNum, PlayerEntity player, int techCost) {
        player.setTechResource(player.getTechResource() - techCost);
        if (unitNum > 0) {
            currentUnit.setUnitNum(currentUnit.getUnitNum() - unitNum);
            upgradedUnit.setUnitNum(upgradedUnit.getUnitNum() + unitNum);
        }
    }

    @Override
    public void executeOnGame(OrderEntity order, GameEntity game) {
        TerritoryEntity source = game.getMap().getTerritoryEntityById(order.getSource().getId());
        PlayerEntity player = game.getPlayerEntityById(order.getPlayer().getId());
        UnitEntity currentUnit = getUnitForType(source, order.getUnitType());
        UnitEntity upgradedUnit = getUnitForType(source, getNextLevel(order.getUnitType()));

        executeLocally(currentUnit, upgradedUnit, order.getUnitNum(), player, UnitUpgradeTechResourceChecker.getTechCost(order));
        unitService.updateUnit(currentUnit, currentUnit.getUnitNum());
        unitService.updateUnit(upgradedUnit, upgradedUnit.getUnitNum());
        playerService.updatePlayerTechResource(player, player.getTechResource());
    }

}
