package edu.duke.ece651.team13.server.rulechecker;

import edu.duke.ece651.team13.server.entity.OrderEntity;
import edu.duke.ece651.team13.server.util.GraphUtill;

public class MovePathChecker extends RuleChecker{
    public MovePathChecker(RuleChecker next) {
        super(next);
    }

    @Override
    protected void checkMyRule(OrderEntity order) throws IllegalArgumentException{
        if(!GraphUtill.hasPath(order.getSource(), order.getDestination())){
            throw new IllegalArgumentException("Invalid move order: There is not a valid path between the src and dst.");
        }
    }

}
