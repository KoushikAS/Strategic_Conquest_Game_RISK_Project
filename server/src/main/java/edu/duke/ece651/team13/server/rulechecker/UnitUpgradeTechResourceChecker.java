package edu.duke.ece651.team13.server.rulechecker;

import edu.duke.ece651.team13.server.entity.OrderEntity;
import edu.duke.ece651.team13.server.entity.PlayerEntity;
import lombok.extern.slf4j.Slf4j;

import static edu.duke.ece651.team13.server.enums.UnitMappingEnum.getNextLevel;
import static edu.duke.ece651.team13.server.util.GraphUtil.findMinCost;

/**
 * Check if the player's tech resource totals is enough for the unit upgrade
 */
public class UnitUpgradeTechResourceChecker extends RuleChecker {
    public UnitUpgradeTechResourceChecker(RuleChecker next) {
        super(next);
    }

    @Override
    protected void checkMyRule(OrderEntity order, PlayerEntity player) throws IllegalArgumentException {
        int techResource = player.getTechResource();
        int cost = getTechCost(order);
        if (techResource < cost) {
            throw new IllegalArgumentException("Invalid Unit upgrade order: Player doesn't have sufficient tech resource.");
        }
    }

    public static int getTechCost(OrderEntity order) {
        return order.getUnitNum() * (getNextLevel(order.getUnitType()).getCost() - order.getUnitType().getCost());
    }
}
