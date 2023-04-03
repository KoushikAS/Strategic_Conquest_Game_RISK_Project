package edu.duke.ece651.team13.server.rulechecker;

import edu.duke.ece651.team13.server.entity.OrderEntity;
import edu.duke.ece651.team13.server.order.Order;
import edu.duke.ece651.team13.server.util.GraphUtill;
import edu.duke.ece651.team13.shared.territory.TerritoryRO;

import java.util.HashSet;
import java.util.Iterator;

import static edu.duke.ece651.team13.shared.util.graphUtil.DFS;

public class MovePathChecker extends RuleChecker{
    public MovePathChecker(RuleChecker next) {
        super(next);
    }

    @Override
    protected String checkMyRule(Order order) {
        if(!hasPath(order.getSource(), order.getDestination())){
            return "Invalid move order: There is not a valid path between the src and dst.";
        }
        return null;
    }

    @Override
    protected String checkMyRule(OrderEntity order) {
        if(!GraphUtill.hasPath(order.getSource(), order.getDestination())){
            return "Invalid move order: There is not a valid path between the src and dst.";
        }
        return null;
    }

    /**
     * Do a DFS from the source territory to find a path to the destination
     * Skip the paths where the owner is not the owner of source
     *
     */
    private boolean hasPath(TerritoryRO source, TerritoryRO destination){
        if(source == destination) return true;

        HashSet<TerritoryRO> visited = new HashSet<>();
        DFS(source, visited, source.getOwner());

        return visited.contains(destination);
    }
}
