package edu.duke.ece651.team13.shared.util;

import edu.duke.ece651.team13.shared.player.Player;
import edu.duke.ece651.team13.shared.player.PlayerRO;
import edu.duke.ece651.team13.shared.territory.Territory;
import edu.duke.ece651.team13.shared.territory.TerritoryRO;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class graphUtil {

    private graphUtil() {
    }

    public static boolean isConnectedGraph(ArrayList<TerritoryRO> territories) {
        // Take a boolean visited array, boolean default to false
        HashSet<TerritoryRO> visited = new HashSet<>();

        // Start the DFS from vertex 0
        DFS(territories.get(0), visited, null);

        // Check if all the vertices are visited
        for (TerritoryRO t : territories) {
            if (!visited.contains(t)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Do a DFS from the source territory, mark each visited territory
     * as true in the visited boolean array of the Owner if it is provided otherwise do for the entire map
     *
     * @param visited is the HashSet to track the visited territories
     */
    public static void DFS(TerritoryRO source, HashSet<TerritoryRO> visited, PlayerRO owner) {
        visited.add(source);
        Iterator<TerritoryRO> it = source.getNeighbourIterartor();
        while (it.hasNext()) {
            TerritoryRO neighbor = it.next();
            if (!visited.contains(neighbor) && (owner == null || owner.equals(neighbor.getOwner()))) {
                DFS(neighbor, visited, owner);
            }
        }
    }
}
