package edu.duke.ece651.team13.shared.util;

import edu.duke.ece651.team13.shared.territory.TerritoryRO;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class graphUtil {

    private graphUtil() {
    }

    public static boolean isConnectedGraph(ArrayList<TerritoryRO> territories) {
        // Take a boolean visited array, boolean default to false
        HashSet<String> visited = new HashSet<>();

        // Start the DFS from vertex 0
        DFS(territories.get(0), visited);

        // Check if all the vertices are visited
        for (TerritoryRO t : territories) {
            if (!visited.contains(t.getName())) {
                return false;
            }
        }
        return true;
    }

    /**
     * Do a DFS from the source territory, mark each visited territory
     * as true in the visited boolean array
     *
     * @param visited is the HashSet to track the visited territories
     */
    private static void DFS(TerritoryRO source, HashSet<String> visited) {
        visited.add(source.getName());
        Iterator<TerritoryRO> it = source.getNeighbourIterartor();
        while (it.hasNext()) {
            TerritoryRO neighbor = it.next();
            if (!visited.contains(neighbor.getName())) {
                DFS(neighbor, visited);
            }
        }
    }
}
