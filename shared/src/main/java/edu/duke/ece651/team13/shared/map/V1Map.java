package edu.duke.ece651.team13.shared.map;

import edu.duke.ece651.team13.shared.territory.Territory;
import edu.duke.ece651.team13.shared.territory.TerritoryRO;
import org.checkerframework.checker.units.qual.A;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Optional;

public abstract class V1Map implements MapRO, Serializable {
    protected ArrayList<Territory> territories;

    /**
     * Construct the V1Map
     * Precondition: initialUnit > 0, or throw IllegalArgumentException
     *
     * @param initialUnit is the initial unit number that could be used by each
     *                    player
     */
    public V1Map(int initialUnit) {
        if (initialUnit <= 0)
            throw new IllegalArgumentException("The initialUnit must be >0");
        this.territories = new ArrayList<>();
        initMap(initialUnit);
        assert (isConnected());
    }

    public V1Map(V1Map toCopy) {
        territories = new ArrayList<>();

        for (Territory t : toCopy.territories) {
            Territory cloneT = t.replicate();
            territories.add(cloneT);
        }
        for (Territory t : toCopy.territories) {
            Territory cloneT = getTerritoryByName(t.getName());
            for (Iterator<TerritoryRO> it = t.getNeighbourIterartor(); it.hasNext(); ) {
                String neighborName = it.next().getName();
                Territory cloneNeighbor = getTerritoryByName(neighborName);
                cloneT.addNeighbours(cloneNeighbor);
            }
        }
    }


    public Iterator<Territory> getTerritoriesIterator() {
        return territories.iterator();
    }

    @Override
    public Iterator<TerritoryRO> getTerritoriesROIterator() {

        ArrayList<TerritoryRO> territoryROS = new ArrayList<>(territories);

        return territoryROS.iterator();
    }

    @Override
    public boolean isConnected() {
        // Take a boolean visited array, boolean default to false
        HashSet<String> visited = new HashSet<>();

        // Start the DFS from vertex 0
        DFS(territories.get(0), visited);

        // Check if all the vertices are visited
        for (Territory t : territories) {
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
    private void DFS(TerritoryRO source, HashSet<String> visited) {
        visited.add(source.getName());
        Iterator<TerritoryRO> it = source.getNeighbourIterartor();
        while (it.hasNext()) {
            TerritoryRO neighbor = it.next();
            if (!visited.contains(neighbor.getName())) {
                DFS(neighbor, visited);
            }
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other != null && other.getClass().equals(getClass())) {
            V1Map otherMap = (V1Map) other;
            Iterator<Territory> otherterritories = otherMap.getTerritoriesIterator();
            Iterator<Territory> territories = getTerritoriesIterator();
            if ((!territories.hasNext() && otherterritories.hasNext()) ||
                    (territories.hasNext() && !otherterritories.hasNext())) {
                return false;
            }
            while (otherterritories.hasNext()) {
                TerritoryRO otherterritory = otherterritories.next();
                TerritoryRO territory = territories.next();
                if (!otherterritory.equals(territory)) {
                    return false;
                }
            }
            return !territories.hasNext();
        }

        return false;
    }

    /**
     * HelperFunction to add to neighbourlist of territory
     * pre condition : Both territory cannot be same
     *
     * @param t1: territory t1
     * @param t2: territory t2
     **/
    protected void addTerritoriesNeighbours(Territory t1, Territory t2) {
        t1.addNeighbours(t2);
        t2.addNeighbours(t1);
    }

    /**
     * Helper function to initialize the map structure
     * - all the territories and proper neighboring relationship
     */
    protected abstract void initMap(int initialUnit);

    @Override
    public Territory getTerritoryByName(String name) {
        Optional<Territory> territory = territories.stream().filter(t -> t.getName().equals(name)).findAny();
        if (!territory.isPresent()) {
            throw new IllegalArgumentException("There is no Territory in this map with the name " + name);
        }
        return territory.get();
    }

    /**
     * Get the list of iterators for all groups (2 groups, 3 groups or 4 groups)
     */
    @Override
    public abstract ArrayList<Iterator<Territory>> getInitialGroups();
}
