package edu.duke.ece651.team13.server.util;

import edu.duke.ece651.team13.server.entity.PlayerEntity;
import edu.duke.ece651.team13.server.entity.TerritoryConnectionEntity;
import edu.duke.ece651.team13.server.entity.TerritoryEntity;
import edu.duke.ece651.team13.shared.territory.TerritoryRO;

import java.util.HashSet;
import java.util.Iterator;
import java.util.stream.Collectors;

import static edu.duke.ece651.team13.shared.util.graphUtil.DFS;

public class GraphUtill {

    /**
     * Do a DFS from the source territory, mark each visited territory
     * as true in the visited boolean array of the Owner if it is provided otherwise do for the entire map
     *
     * @param visited is the HashSet to track the visited territories
     */
    public static void DFS(TerritoryEntity source, HashSet<TerritoryEntity> visited, PlayerEntity owner) {
        visited.add(source);
        Iterator<TerritoryEntity> it = source.getConnections().stream().map(TerritoryConnectionEntity::getDestinationTerritory).collect(Collectors.toList()).iterator();
        while (it.hasNext()) {
            TerritoryEntity neighbor = it.next();
            if (!visited.contains(neighbor) && (owner == null || owner.equals(neighbor.getOwner()))) {
                DFS(neighbor, visited, owner);
            }
        }
    }

    /**
     * Do a DFS from the source territory to find a path to the destination
     * Skip the paths where the owner is not the owner of source
     *
     */
    public static boolean hasPath(TerritoryEntity source, TerritoryEntity destination){
        if(source == destination) return true;

        HashSet<TerritoryEntity> visited = new HashSet<>();
        DFS(source, visited, source.getOwner());

        return visited.contains(destination);
    }
}
