package edu.duke.ece651.team13.server.util;

import edu.duke.ece651.team13.server.entity.PlayerEntity;
import edu.duke.ece651.team13.server.entity.TerritoryConnectionEntity;
import edu.duke.ece651.team13.server.entity.TerritoryEntity;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class GraphUtil {

    /**
     * Do a DFS from the source territory, mark each visited territory
     * as true in the visited boolean array of the Owner if it is provided otherwise do for the entire map
     *
     * @param visited is the HashSet to track the visited territories
     */
    public static void DFS(TerritoryEntity source, HashSet<TerritoryEntity> visited, PlayerEntity owner) {
        visited.add(source);
        List<TerritoryEntity> neighbors = source.getConnections().stream().map(TerritoryConnectionEntity::getDestinationTerritory).collect(Collectors.toList());
        for (TerritoryEntity neighbor : neighbors) {
            if (!visited.contains(neighbor) && (owner == null || owner.equals(neighbor.getOwner()))) {
                DFS(neighbor, visited, owner);
            }
        }
    }

    /**
     * Do a DFS from the source territory to find a path to the destination
     * Skip the paths where the owner is not the owner of source
     */
    public static boolean hasPath(TerritoryEntity source, TerritoryEntity destination) {
        if (source == destination) return true;

        HashSet<TerritoryEntity> visited = new HashSet<>();
        DFS(source, visited, source.getOwner());

        return visited.contains(destination);
    }

    /**
     * Find the minimum cost valid path between source and destination territory
     *
     * @return the minimum cost if a path exists
     * Integer.MAX_VALUE if no valid path exists
     */
    public static int minimumCostPath(TerritoryEntity source,
                                      TerritoryEntity destination,
                                      HashSet<TerritoryEntity> visited,
                                      PlayerEntity owner) {
        // Base case: already reached destination, cost is 0
        if (source.equals(destination)) return 0;

        // Mark the current node as visited
        visited.add(source);
        int cost = Integer.MAX_VALUE;
        // Traverse through neighbors
        List<TerritoryConnectionEntity> connections = source.getConnections();
        for (TerritoryConnectionEntity connection : connections) {
            TerritoryEntity neighbor = connection.getDestinationTerritory();
            if (!visited.contains(neighbor) && (owner == null || owner.equals(neighbor.getOwner()))) {
                visited.add(neighbor);
                int neighborCost = minimumCostPath(neighbor, destination, visited, owner);

                // Check if we have reached the destination
                if (neighborCost < Integer.MAX_VALUE) {
                    // Min cost path
                    cost = Math.min(cost, connection.getDistance() + neighborCost);
                }
                visited.remove(neighbor);
            }
        }
        // Unmark the current node to make it available for other paths
        visited.remove(source);
        return cost;
    }

    /**
     * Find the minimum cost valid path between source and destination territory for a spy unit
     * Spy unit can only visit one enemy territory in the path
     *
     * @return the minimum cost if a path exists
     * Integer.MAX_VALUE if no valid path exists
     */
    public static int minimumCostPathForSpy(TerritoryEntity source,
                                      TerritoryEntity destination,
                                      HashSet<TerritoryEntity> visited,
                                      PlayerEntity owner,
                                      boolean hasVisitedEnemy) {
        // Base case: already reached destination, cost is 0
        if (source.equals(destination)) return 0;

        // Mark the current node as visited
        visited.add(source);
        int cost = Integer.MAX_VALUE;
        // Traverse through neighbors
        List<TerritoryConnectionEntity> connections = source.getConnections();
        for (TerritoryConnectionEntity connection : connections) {
            TerritoryEntity neighbor = connection.getDestinationTerritory();
            if (!visited.contains(neighbor)) {
                if (!owner.equals(neighbor.getOwner())){
                    if(hasVisitedEnemy) return cost;
                    else hasVisitedEnemy = true;
                }
                visited.add(neighbor);
                int neighborCost = minimumCostPathForSpy(neighbor, destination, visited, owner, hasVisitedEnemy);

                // Check if we have reached the destination
                if (neighborCost < Integer.MAX_VALUE) {
                    // Min cost path
                    cost = Math.min(cost, connection.getDistance() + neighborCost);
                }
                visited.remove(neighbor);
            }
        }
        // Unmark the current node to make it available for other paths
        visited.remove(source);
        return cost;
    }

    /**
     * Find the minimum cost valid path between source and destination territory
     *
     * @return the minimum cost if a path exists
     * Integer.MAX_VALUE if no valid path exists
     */
    public static int findMinCost(TerritoryEntity source, TerritoryEntity destination) {
        HashSet<TerritoryEntity> visited = new HashSet<>();
        return minimumCostPath(source, destination, visited, source.getOwner());
    }

    /**
     * Find the minimum cost valid path between source and destination territory for spy unit
     *
     * @return the minimum cost if a path exists
     * Integer.MAX_VALUE if no valid path exists
     */
    public static int findMinCostForSpy(TerritoryEntity source, TerritoryEntity destination, PlayerEntity player) {
        HashSet<TerritoryEntity> visited = new HashSet<>();
        return minimumCostPathForSpy(source, destination, visited, player, false);
    }
}
