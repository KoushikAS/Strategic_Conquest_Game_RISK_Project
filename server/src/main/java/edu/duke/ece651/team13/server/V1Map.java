package edu.duke.ece651.team13.server;

import java.util.ArrayList;

public class V1Map implements Map {
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

        //Init Map
        // TODO: Change the graph
        // Two territories neighboring to each other
        Territory t1 = new GameTerritory(0, "t1");
        Territory t2 = new GameTerritory(1, "t2");
        t1.addNeighbor(t2);
        t2.addNeighbor(t1);
        this.territories.add(t1);
        this.territories.add(t2);
    }

//    @Override
//    public void assignTerritories(ArrayList<Player> players) {
//
//    }

//    @Override
//    public void initMap() {
//    }
}
