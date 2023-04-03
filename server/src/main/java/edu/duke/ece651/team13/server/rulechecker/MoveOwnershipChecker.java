package edu.duke.ece651.team13.server.rulechecker;

import edu.duke.ece651.team13.server.entity.OrderEntity;
import edu.duke.ece651.team13.server.order.Order;

/**
 * Check if the source territory of the order has valid ownership
 */
public class MoveOwnershipChecker extends RuleChecker{

    public MoveOwnershipChecker(RuleChecker next) {
        super(next);
    }

    @Override
    protected String checkMyRule(Order order) {
        if (order.getSource().getOwner() != order.getPlayer()) {
            return "Invalid move order: The source territory is not owned by you.";
        } else if (order.getDestination().getOwner() != order.getPlayer()) {
            return "Invalid move order: The destination territory is not owned by you.";
        }
        return null;
    }

    @Override
    protected String checkMyRule(OrderEntity order) {
        if (order.getSource().getOwner() != order.getPlayer()) {
            return "Invalid move order: The source territory is not owned by you.";
        } else if (order.getDestination().getOwner() != order.getPlayer()) {
            return "Invalid move order: The destination territory is not owned by you.";
        }
        return null;
    }
}
