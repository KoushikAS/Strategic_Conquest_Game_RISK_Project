package edu.duke.ece651.team13.shared.rulechecker;

import edu.duke.ece651.team13.shared.order.Order;

/**
 * Checks for correct ownership for the source and destination territories,
 * i.e., source territory is controlled by the player, and
 * destination territory is controlled by a different player
 */
public class AttackOwnershipChecker extends RuleChecker {
    public AttackOwnershipChecker(RuleChecker next) {
        super(next);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String checkMyRule(Order order) {
        if (order.getSource().getOwner() != order.getPlayer()) {
            return "Invalid attack order: The source territory is not owned by you.";
        }
        if (order.getDestination().getOwner() == order.getPlayer()) {
            return "Invalid attack order: The destination territory cannot be owned by you.";
        }
        return null;
    }
}
