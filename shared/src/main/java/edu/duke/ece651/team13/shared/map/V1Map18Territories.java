package edu.duke.ece651.team13.shared.map;

import edu.duke.ece651.team13.shared.territory.GameTerritory;
import edu.duke.ece651.team13.shared.territory.Territory;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * drawio link to view map:
 * https://drive.google.com/file/d/1Fwy_GMuMYU3z7koQqVjbW5Y22vkXzESr/view?usp=sharing
 */
public class V1Map18Territories extends V1Map {

    /**
     * Construct the V1Map18Territory
     * Precondition: initialUnit > 0, or throw IllegalArgumentException
     *
     * @param initialUnit is the initial unit number that could be used by each
     *                    player
     */
    public V1Map18Territories(int initialUnit) {
        super(initialUnit);
    }

    /**
     * Copy constructor
     */
    private V1Map18Territories(V1Map18Territories toCopy){
        super(toCopy);
    }

    /**
     * Helper function to initialize the map structure
     * - all the territories and proper neighboring relationship
     */
    @Override
    protected void initMap(int initialUnit) {
        // Creating Terrritores
        Territory rottweiler = new GameTerritory("Rottweiler", initialUnit);
        Territory dachshund = new GameTerritory("Dachshund", initialUnit);
        Territory beagle = new GameTerritory("Beagle", initialUnit);
        Territory labrador = new GameTerritory("Labrador", initialUnit);
        Territory poodle = new GameTerritory("Poodle", initialUnit);
        Territory bulldog = new GameTerritory("Bulldog", initialUnit);
        Territory boxer = new GameTerritory("Boxer", initialUnit);
        Territory havanese = new GameTerritory("Havanese", initialUnit);
        Territory spaniel = new GameTerritory("Spaniel", initialUnit);
        Territory sheepdog = new GameTerritory("Sheepdog", initialUnit);
        Territory akita = new GameTerritory("Akita", initialUnit);
        Territory brittany = new GameTerritory("Brittany", initialUnit);
        Territory vizsla = new GameTerritory("Vizsla", initialUnit);
        Territory pug = new GameTerritory("Pug", initialUnit);
        Territory chihuahua = new GameTerritory("Chihuahua", initialUnit);
        Territory maltese = new GameTerritory("Maltese", initialUnit);
        Territory mastiff = new GameTerritory("Mastiff", initialUnit);
        Territory collie = new GameTerritory("Collie", initialUnit);

        addTerritoriesNeighbours(labrador, bulldog);
        addTerritoriesNeighbours(labrador, rottweiler);
        addTerritoriesNeighbours(labrador, dachshund);
        addTerritoriesNeighbours(dachshund, bulldog);
        addTerritoriesNeighbours(bulldog, beagle);
        addTerritoriesNeighbours(rottweiler, dachshund);
        addTerritoriesNeighbours(dachshund, beagle);
        addTerritoriesNeighbours(rottweiler, spaniel);
        addTerritoriesNeighbours(rottweiler, havanese);
        addTerritoriesNeighbours(rottweiler, poodle);
        addTerritoriesNeighbours(dachshund, poodle);
        addTerritoriesNeighbours(beagle, poodle);
        addTerritoriesNeighbours(beagle, vizsla);
        addTerritoriesNeighbours(beagle, pug);
        addTerritoriesNeighbours(poodle, spaniel);
        addTerritoriesNeighbours(poodle, havanese);
        addTerritoriesNeighbours(poodle, vizsla);
        addTerritoriesNeighbours(poodle, pug);
        addTerritoriesNeighbours(havanese, vizsla);
        addTerritoriesNeighbours(spaniel, havanese);
        addTerritoriesNeighbours(brittany, havanese);
        addTerritoriesNeighbours(sheepdog, havanese);
        addTerritoriesNeighbours(spaniel, brittany);
        addTerritoriesNeighbours(brittany, sheepdog);
        addTerritoriesNeighbours(spaniel, boxer);
        addTerritoriesNeighbours(brittany, boxer);
        addTerritoriesNeighbours(brittany, akita);
        addTerritoriesNeighbours(sheepdog, akita);
        addTerritoriesNeighbours(boxer, akita);
        addTerritoriesNeighbours(vizsla, pug);
        addTerritoriesNeighbours(vizsla, chihuahua);
        addTerritoriesNeighbours(vizsla, maltese);
        addTerritoriesNeighbours(pug, chihuahua);
        addTerritoriesNeighbours(chihuahua, maltese);
        addTerritoriesNeighbours(mastiff, collie);
        addTerritoriesNeighbours(pug, mastiff);
        addTerritoriesNeighbours(chihuahua, mastiff);
        addTerritoriesNeighbours(chihuahua, collie);
        addTerritoriesNeighbours(maltese, collie);
        addTerritoriesNeighbours(havanese, maltese);
        addTerritoriesNeighbours(vizsla, sheepdog);
        addTerritoriesNeighbours(sheepdog, maltese);


        territories.add(rottweiler);
        territories.add(dachshund);
        territories.add(beagle);
        territories.add(labrador);
        territories.add(poodle);
        territories.add(bulldog);
        territories.add(boxer);
        territories.add(havanese);
        territories.add(spaniel);
        territories.add(sheepdog);
        territories.add(akita);
        territories.add(brittany);
        territories.add(vizsla);
        territories.add(pug);
        territories.add(chihuahua);
        territories.add(maltese);
        territories.add(mastiff);
        territories.add(collie);
    }

    @Override
    public ArrayList<Iterator<Territory>> getInitialGroups(){
        ArrayList<ArrayList<Territory>> groups = new ArrayList<>();


        //make groups
        ArrayList<Territory> group1 = new ArrayList<>();
        ArrayList<Territory> group2 = new ArrayList<>();
        ArrayList<Territory> group3 = new ArrayList<>();

        group1.add(getTerritoryByName("Rottweiler"));
        group1.add(getTerritoryByName("Dachshund"));
        group1.add(getTerritoryByName("Beagle"));
        group1.add(getTerritoryByName("Labrador"));
        group1.add(getTerritoryByName("Poodle"));
        group1.add(getTerritoryByName("Bulldog"));

        group2.add(getTerritoryByName("Boxer"));
        group2.add(getTerritoryByName("Havanese"));
        group2.add(getTerritoryByName("Spaniel"));
        group2.add(getTerritoryByName("Sheepdog"));
        group2.add(getTerritoryByName("Akita"));
        group2.add(getTerritoryByName("Brittany"));

        group3.add(getTerritoryByName("Vizsla"));
        group3.add(getTerritoryByName("Maltese"));
        group3.add(getTerritoryByName("Chihuahua"));
        group3.add(getTerritoryByName("Pug"));
        group3.add(getTerritoryByName("Mastiff"));
        group3.add(getTerritoryByName("Collie"));

        groups.add(group1);
        groups.add(group2);
        groups.add(group3);

        ArrayList<Iterator<Territory>> groupsIteratorList = new ArrayList<>();
        for (ArrayList<Territory> group : groups) {
            groupsIteratorList.add(group.iterator());
        }
        return groupsIteratorList;
    }

    @Override
    public MapRO replicate() {
        return new V1Map18Territories(this);
    }
}
