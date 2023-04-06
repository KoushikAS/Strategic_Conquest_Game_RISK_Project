package edu.duke.ece651.team13.server.rulechecker;

import edu.duke.ece651.team13.server.entity.OrderEntity;

/**
 * Check if the source territory's unit number is valid after executing the order
 */
public class MoveUnitNumChecker extends RuleChecker{
    public MoveUnitNumChecker(RuleChecker next) {
        super(next);
    }

    @Override
    protected void checkMyRule(OrderEntity order) throws IllegalArgumentException {
        int sourceUnitNum = order.getSource().getUnitNum();
        long moveUnitNum = order.getUnitNum();
        if(sourceUnitNum < moveUnitNum) {
            throw new IllegalArgumentException("Invalid move order: Don't have sufficient unit number in the territory.");
        }
        else if(moveUnitNum < 0){
            throw new IllegalArgumentException( "Invalid move order: The unit number to move should be >= 0.");
        }
    }
}
