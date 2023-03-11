package edu.duke.ece651.team13.shared;

/**
 * Check if the source territory of the order has valid ownership
 */
public class OwnershipChecker extends RuleChecker{

    public OwnershipChecker(RuleChecker next) {
        super(next);
    }

    @Override
    protected String checkMyRule(PlayerOrder order) {
        if (order.getSource().getOwner() != order.player) {
            return "Invalid move order: The source territory is not owned by you.";
        } else if (order.getDestination().getOwner() != order.player) {
            return "Invalid move order: The destination territory is not owned by you.";
        }
        return null;
    }
}
