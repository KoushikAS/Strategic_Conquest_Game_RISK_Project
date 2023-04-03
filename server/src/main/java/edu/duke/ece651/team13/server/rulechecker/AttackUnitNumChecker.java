package edu.duke.ece651.team13.server.rulechecker;

import edu.duke.ece651.team13.server.entity.OrderEntity;
import edu.duke.ece651.team13.server.order.Order;

/**
 * Check if the source territory's unit number is valid after executing the order
 */
public class AttackUnitNumChecker extends RuleChecker {
    public AttackUnitNumChecker(RuleChecker next) {
        super(next);
    }

    @Override
    protected String checkMyRule(Order order) {
        int sourceUnitNum = order.getSource().getUnitNum();
        int attackUnitNum = order.getUnits();
        if (sourceUnitNum < attackUnitNum) {
            return "Invalid attack order: Don't have sufficient unit number in the territory.";
        } else if (attackUnitNum < 0) {
            return "Invalid attack order: The unit number to move should be >= 0.";
        }
        return null;
    }

    @Override
    protected String checkMyRule(OrderEntity order) {
        int sourceUnitNum = order.getSource().getUnitNum();
        long attackUnitNum = order.getUnitNum();
        if (sourceUnitNum < attackUnitNum) {
            return "Invalid attack order: Don't have sufficient unit number in the territory.";
        } else if (attackUnitNum < 0) {
            return "Invalid attack order: The unit number to move should be >= 0.";
        }
        return null;
    }
}
