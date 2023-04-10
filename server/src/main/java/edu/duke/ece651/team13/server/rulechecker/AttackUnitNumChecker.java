package edu.duke.ece651.team13.server.rulechecker;

import edu.duke.ece651.team13.server.entity.OrderEntity;
import edu.duke.ece651.team13.server.entity.PlayerEntity;
import edu.duke.ece651.team13.server.enums.UnitMappingEnum;

import static edu.duke.ece651.team13.server.service.TerritoryService.getUnitForType;

/**
 * Check if the source territory's unit number is valid after executing the order
 */
public class AttackUnitNumChecker extends RuleChecker {
    public AttackUnitNumChecker(RuleChecker next) {
        super(next);
    }

    @Override
    protected void checkMyRule(OrderEntity order, PlayerEntity player) throws IllegalArgumentException {
        UnitMappingEnum unitType = order.getUnitType();
        int sourceUnitNum = getUnitForType(order.getSource(), unitType).getUnitNum();
        int attackUnitNum = order.getUnitNum();
        if (sourceUnitNum < attackUnitNum) {
            throw new IllegalArgumentException("Invalid attack order: Don't have sufficient unit number in the territory.");
        } else if (attackUnitNum < 0) {
            throw new IllegalArgumentException("Invalid attack order: The unit number to move should be >= 0.");
        }
    }
}
