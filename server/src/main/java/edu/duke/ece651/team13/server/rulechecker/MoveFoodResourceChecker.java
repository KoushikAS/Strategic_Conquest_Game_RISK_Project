package edu.duke.ece651.team13.server.rulechecker;

import edu.duke.ece651.team13.server.entity.OrderEntity;

import static edu.duke.ece651.team13.server.util.GraphUtil.findMinCost;

public class MoveFoodResourceChecker extends RuleChecker {
    public MoveFoodResourceChecker(RuleChecker next) {
        super(next);
    }

    @Override
    protected void checkMyRule(OrderEntity order) throws IllegalArgumentException {
        int foodResource = order.getPlayer().getFoodResource();
        int cost = getFoodCost(order);
        if (foodResource < cost) {
            throw new IllegalArgumentException("Invalid move order: Player doesn't have sufficient food resource.");
        }
    }

    public static int getFoodCost(OrderEntity order) {
        return 2 * findMinCost(order.getSource(), order.getDestination()) * order.getUnitNum();
    }
}
