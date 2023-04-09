package edu.duke.ece651.team13.server.rulechecker;

import edu.duke.ece651.team13.server.entity.OrderEntity;

/**
 * Check if the player's food resource totals is enough for the attack order
 */
public class AttackFoodResourceChecker extends RuleChecker {
    public AttackFoodResourceChecker(RuleChecker next) {
        super(next);
    }

    @Override
    protected void checkMyRule(OrderEntity order) throws IllegalArgumentException {
        int foodResource = order.getPlayer().getFoodResource();
        int cost = getFoodCost(order);
        if (foodResource < cost) {
            throw new IllegalArgumentException("Invalid attack order: Player doesn't have sufficient food resource.");
        }
    }

    public static int getFoodCost(OrderEntity order) {
        return 10 * order.getTotalUnitNum();
    }
}
