package edu.duke.ece651.team13.shared.rulechecker;

import edu.duke.ece651.team13.shared.Territory;
import edu.duke.ece651.team13.shared.order.PlayerOrder;

import java.util.HashSet;
import java.util.Iterator;

public class MovePathChecker extends RuleChecker{
    public MovePathChecker(RuleChecker next) {
        super(next);
    }

    @Override
    protected String checkMyRule(PlayerOrder order) {
        if(!hasPath(order.getSource(), order.getDestination(), new HashSet<>())){
            return "Invalid move order: There is not a valid path between the src and dst.";
        }
        return null;
    }

    /**
     * Do a DFS from the source territory to find a path to the destination
     * Skip the paths where the owner is not the owner of source
     *
     * @param visited is the HashSet to track the visited territories
     */
    private boolean hasPath(Territory source, Territory destination, HashSet<Integer> visited){
        if(source == destination) return true;

        visited.add(source.getId());
        Iterator<Territory> it = source.getNeighbourIterartor();
        while(it.hasNext()){
            Territory neighbor = it.next();
            if(neighbor.getOwner() == source.getOwner()
                && !visited.contains(neighbor.getId())){
                if(hasPath(neighbor, destination, visited))
                    return true;
            }
        }
        return false;
    }
}
