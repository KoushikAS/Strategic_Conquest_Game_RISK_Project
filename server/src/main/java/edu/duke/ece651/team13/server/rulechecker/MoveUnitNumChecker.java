package edu.duke.ece651.team13.server.rulechecker;

import edu.duke.ece651.team13.server.order.Order;

/**
 * Check if the source territory's unit number is valid after executing the order
 */
public class MoveUnitNumChecker extends RuleChecker{
    public MoveUnitNumChecker(RuleChecker next) {
        super(next);
    }

    @Override
    protected String checkMyRule(Order order) {
        int sourceUnitNum = order.getSource().getUnitNum();
        int moveUnitNum = order.getUnits();
        if(sourceUnitNum < moveUnitNum) {
            return "Invalid move order: Don't have sufficient unit number in the territory.";
        }
        else if(moveUnitNum < 0){
            return "Invalid move order: The unit number to move should be >= 0.";
        }
        return null;
    }
}
