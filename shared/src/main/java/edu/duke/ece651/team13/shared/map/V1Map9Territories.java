package edu.duke.ece651.team13.shared.map;

import edu.duke.ece651.team13.shared.territory.GameTerritory;
import edu.duke.ece651.team13.shared.territory.Territory;

import java.util.ArrayList;
import java.util.Iterator;

public class V1Map9Territories extends V1Map {

    /**
     * Construct the V1Map
     * Precondition: initialUnit > 0, or throw IllegalArgumentException
     *
     * @param initialUnit is the initial unit number that could be used by each
     *                    player
     */
    public V1Map9Territories(int initialUnit) {
        super(initialUnit);
    }

    /**
     * Copy constructor
     */
    private V1Map9Territories(V1Map9Territories toCopy) {
        super(toCopy);
    }

    /**
     * Helper function to initialize the map structure
     * - all the territories and proper neighboring relationship
     */
    protected void initMap(int initialUnit) {
        // Creating Terrritores
        Territory narnia = new GameTerritory("Narnia", initialUnit);
        Territory midkemia = new GameTerritory("Midkemia", initialUnit);
        Territory oz = new GameTerritory("Oz", initialUnit);
        Territory gondor = new GameTerritory("Gondor", initialUnit);
        Territory elantris = new GameTerritory("Elantris", initialUnit);
        Territory scadrial = new GameTerritory("Scadrial", initialUnit);
        Territory roshar = new GameTerritory("Roshar", initialUnit);
        Territory hogwarts = new GameTerritory("Hogwarts", initialUnit);
        Territory mordor = new GameTerritory("Mordor", initialUnit);

        addTerritoriesNeighbours(narnia, midkemia);
        addTerritoriesNeighbours(narnia, elantris);
        addTerritoriesNeighbours(midkemia, oz);
        addTerritoriesNeighbours(midkemia, scadrial);
        addTerritoriesNeighbours(oz, gondor);
        addTerritoriesNeighbours(oz, mordor);
        addTerritoriesNeighbours(gondor, mordor);
        addTerritoriesNeighbours(elantris, scadrial);
        addTerritoriesNeighbours(elantris, roshar);
        addTerritoriesNeighbours(scadrial, roshar);
        addTerritoriesNeighbours(scadrial, mordor);
        addTerritoriesNeighbours(scadrial, hogwarts);
        addTerritoriesNeighbours(roshar, hogwarts);
        addTerritoriesNeighbours(mordor, hogwarts);

        territories.add(narnia);
        territories.add(midkemia);
        territories.add(oz);
        territories.add(gondor);
        territories.add(elantris);
        territories.add(scadrial);
        territories.add(roshar);
        territories.add(hogwarts);
        territories.add(mordor);
    }

    @Override
    public ArrayList<Iterator<Territory>> getInitialGroups() {
        ArrayList<ArrayList<Territory>> groups = new ArrayList<>();

        ArrayList<Territory> group1 = new ArrayList<>();
        ArrayList<Territory> group2 = new ArrayList<>();
        ArrayList<Territory> group3 = new ArrayList<>();

        group1.add(getTerritoryByName("Narnia"));
        group1.add(getTerritoryByName("Midkemia"));
        group1.add(getTerritoryByName("Oz"));

        group2.add(getTerritoryByName("Gondor"));
        group2.add(getTerritoryByName("Mordor"));
        group2.add(getTerritoryByName("Hogwarts"));

        group3.add(getTerritoryByName("Elantris"));
        group3.add(getTerritoryByName("Scadrial"));
        group3.add(getTerritoryByName("Roshar"));

        ArrayList<Iterator<Territory>> groupsIteratorList = new ArrayList<>();
        for (ArrayList<Territory> group : groups) {
            groupsIteratorList.add(group.iterator());
        }
        return groupsIteratorList;
    }

    @Override
    public MapRO replicate() {
        return new V1Map9Territories(this);
    }
}
