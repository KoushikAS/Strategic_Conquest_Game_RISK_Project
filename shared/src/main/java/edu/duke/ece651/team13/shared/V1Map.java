package edu.duke.ece651.team13.shared;

import java.util.ArrayList;
import java.io.Serializable;

public class V1Map implements Map, Serializable{
    private ArrayList<Territory> territories;
    private final int initialUnit;

    @Override
    public int getInitialUnit() {
        return initialUnit;
    }

    /**
     * Construct the V1Map
     * Precondition: initialUnit > 0, or throw IllegalArgumentException
     *
     * @param initialUnit is the initial unit number that could be used by each player
     */
    public V1Map(int initialUnit){
        if(initialUnit <= 0) throw new IllegalArgumentException("The initialUnit must be >0");
        this.territories = new ArrayList<>();
        this.initialUnit = initialUnit;
        initMap();
    }

    @Override
    //todo: More conditions are needed to determine that two maps are equal
    public boolean equals(Object other){
        if(other !=null && other.getClass().equals(getClass())){
            V1Map otherV1Map = (V1Map) other;
            return otherV1Map.getInitialUnit() == this.getInitialUnit();
        }
        return false;
    }

//    @Override
//    public void assignTerritories(ArrayList<Player> players) {
//
//    }

    /**
     * Helper function to initialize the map structure
     * - all the territories and proper neighboring relationship
     */
    private void initMap() {
        // TODO: Change the graph
        // Two territories neighboring to each other
        Territory t1 = new GameTerritory("t1");
        Territory t2 = new GameTerritory("t2");
        // TODO: Add neighbors
        tryAddTerritory(t1);
        tryAddTerritory(t2);
    }

    private void tryAddTerritory(Territory toAdd){
        // TODO: Add a verification of connected graph here?
        territories.add(toAdd);
    }
}
