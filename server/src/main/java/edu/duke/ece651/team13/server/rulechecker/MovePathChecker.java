package edu.duke.ece651.team13.server.rulechecker;

import edu.duke.ece651.team13.server.entity.OrderEntity;
import edu.duke.ece651.team13.server.entity.PlayerEntity;
import edu.duke.ece651.team13.server.util.GraphUtil;

/**
 * Checks if there is a valid path between the source and
 * destination territories in a move order.
 */
public class MovePathChecker extends RuleChecker {
    public MovePathChecker(RuleChecker next) {
        super(next);
    }

    @Override
    protected void checkMyRule(OrderEntity order, PlayerEntity player) throws IllegalArgumentException {
        if (!GraphUtil.hasPath(order.getSource(), order.getDestination())) {
            throw new IllegalArgumentException("Invalid move order: There is not a valid path between the src and dst.");
        }
    }

}
