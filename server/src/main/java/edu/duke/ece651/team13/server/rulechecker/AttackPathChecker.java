package edu.duke.ece651.team13.server.rulechecker;

import edu.duke.ece651.team13.server.order.Order;
import edu.duke.ece651.team13.shared.territory.TerritoryRO;

import java.util.Iterator;

/**
 * Checks if an attack order has the correct path, i.e.,
 * the player can only send units to an adjacent territory
 */
public class AttackPathChecker extends RuleChecker {
    public AttackPathChecker(RuleChecker next) {
        super(next);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String checkMyRule(Order order) {
        if (!isValidPath(order.getSource(), order.getDestination())) {
            return "Invalid attack order: You can only attack an adjacent territory owned by another player.";
        }
        return null;
    }

    /**
     * Checks if the destination territory is a neighbor to the source territory
     *
     * @param source      the source territory
     * @param destination the destination territory
     * @return true if it is a valid path and false otherwise
     */
    private boolean isValidPath(TerritoryRO source, TerritoryRO destination) {
        Iterator<TerritoryRO> it = source.getNeighbourIterartor();
        while (it.hasNext()) {
            TerritoryRO neighbor = it.next();
            if (destination == neighbor) {
                return true;
            }
        }
        return false;
    }
}
