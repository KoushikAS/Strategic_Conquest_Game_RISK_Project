package edu.duke.ece651.team13.shared.map;

import edu.duke.ece651.team13.shared.territory.GameTerritory;
import edu.duke.ece651.team13.shared.territory.Territory;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * drawio link to view map:
 * https://drive.google.com/file/d/1Fwy_GMuMYU3z7koQqVjbW5Y22vkXzESr/view?usp=sharing
 */
public class V1Map24Territories extends V1Map {

    /**
     * Construct the V1Map24Territory
     * Precondition: initialUnit > 0, or throw IllegalArgumentException
     *
     * @param initialUnit is the initial unit number that could be used by each
     *                    player
     */
    public V1Map24Territories(int initialUnit) {
        super(initialUnit);
    }

    /**
     * Copy constructor
     */
    private V1Map24Territories(V1Map24Territories toCopy) {
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
        Territory dalmatian = new GameTerritory("Dalmatian", initialUnit);
        Territory papillon = new GameTerritory("Papillon", initialUnit);
        Territory setter = new GameTerritory("Setter", initialUnit);
        Territory samoyed = new GameTerritory("Samoyed", initialUnit);
        Territory bullmastiff = new GameTerritory("Bullmastiff", initialUnit);
        Territory whippet = new GameTerritory("Whippet", initialUnit);

        addTerritoriesNeighbours(dachshund, beagle);
        addTerritoriesNeighbours(dachshund, rottweiler);
        addTerritoriesNeighbours(dachshund, bulldog);
        addTerritoriesNeighbours(beagle, bulldog);
        addTerritoriesNeighbours(beagle, poodle);
        addTerritoriesNeighbours(rottweiler, bulldog);
        addTerritoriesNeighbours(bulldog, poodle);
        addTerritoriesNeighbours(rottweiler, labrador);
        addTerritoriesNeighbours(bulldog, labrador);
        addTerritoriesNeighbours(poodle, labrador);

        addTerritoriesNeighbours(brittany, sheepdog);
        addTerritoriesNeighbours(brittany, akita);
        addTerritoriesNeighbours(brittany, havanese);
        addTerritoriesNeighbours(sheepdog, havanese);
        addTerritoriesNeighbours(sheepdog, spaniel);
        addTerritoriesNeighbours(akita, havanese);
        addTerritoriesNeighbours(havanese, spaniel);
        addTerritoriesNeighbours(akita, boxer);
        addTerritoriesNeighbours(havanese, boxer);
        addTerritoriesNeighbours(spaniel, boxer);

        addTerritoriesNeighbours(pug, maltese);
        addTerritoriesNeighbours(pug, mastiff);
        addTerritoriesNeighbours(pug, vizsla);
        addTerritoriesNeighbours(maltese, vizsla);
        addTerritoriesNeighbours(maltese, chihuahua);
        addTerritoriesNeighbours(mastiff, vizsla);
        addTerritoriesNeighbours(vizsla, chihuahua);
        addTerritoriesNeighbours(mastiff, collie);
        addTerritoriesNeighbours(vizsla, collie);
        addTerritoriesNeighbours(chihuahua, collie);

        addTerritoriesNeighbours(bullmastiff, whippet);
        addTerritoriesNeighbours(bullmastiff, papillon);
        addTerritoriesNeighbours(bullmastiff, setter);
        addTerritoriesNeighbours(whippet, setter);
        addTerritoriesNeighbours(whippet, samoyed);
        addTerritoriesNeighbours(papillon, setter);
        addTerritoriesNeighbours(setter, samoyed);
        addTerritoriesNeighbours(papillon, dalmatian);
        addTerritoriesNeighbours(setter, dalmatian);
        addTerritoriesNeighbours(samoyed, dalmatian);

        addTerritoriesNeighbours(rottweiler, akita);
        addTerritoriesNeighbours(rottweiler, boxer);
        addTerritoriesNeighbours(poodle, mastiff);
        addTerritoriesNeighbours(labrador, mastiff);

        addTerritoriesNeighbours(spaniel, dalmatian);
        addTerritoriesNeighbours(spaniel, papillon);

        addTerritoriesNeighbours(samoyed, collie);
        addTerritoriesNeighbours(samoyed, chihuahua);

        addTerritoriesNeighbours(boxer, labrador);
        addTerritoriesNeighbours(labrador, collie);
        addTerritoriesNeighbours(collie, dalmatian);
        addTerritoriesNeighbours(dalmatian, boxer);
        addTerritoriesNeighbours(labrador, dalmatian);
        addTerritoriesNeighbours(boxer, collie);

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
        territories.add(pug);
        territories.add(vizsla);
        territories.add(chihuahua);
        territories.add(maltese);
        territories.add(mastiff);
        territories.add(collie);
        territories.add(dalmatian);
        territories.add(papillon);
        territories.add(setter);
        territories.add(samoyed);
        territories.add(bullmastiff);
        territories.add(whippet);


    }

    @Override
    public ArrayList<Iterator<Territory>> getInitialGroups() {
        ArrayList<ArrayList<Territory>> groups = new ArrayList<>();

        ArrayList<Territory> group1 = new ArrayList<>();
        ArrayList<Territory> group2 = new ArrayList<>();
        ArrayList<Territory> group3 = new ArrayList<>();
        ArrayList<Territory> group4 = new ArrayList<>();

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

        group4.add(getTerritoryByName("Dalmatian"));
        group4.add(getTerritoryByName("Papillon"));
        group4.add(getTerritoryByName("Setter"));
        group4.add(getTerritoryByName("Samoyed"));
        group4.add(getTerritoryByName("Bullmastiff"));
        group4.add(getTerritoryByName("Whippet"));

        groups.add(group1);
        groups.add(group2);
        groups.add(group3);
        groups.add(group4);

        ArrayList<Iterator<Territory>> groupsIteratorList = new ArrayList<>();
        for (ArrayList<Territory> group : groups) {
            groupsIteratorList.add(group.iterator());
        }
        return groupsIteratorList;
    }

    @Override
    public MapRO replicate() {
        return new V1Map24Territories(this);
    }
}
