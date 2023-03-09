package edu.duke.ece651.team13.shared;

/**
 * drawio link to view map:
 * https://drive.google.com/file/d/1Fwy_GMuMYU3z7koQqVjbW5Y22vkXzESr/view?usp=sharing
 */
public class V1Map12Territories extends V1Map {

    /**
     * Construct the Map2Player
     * Precondition: initialUnit > 0, or throw IllegalArgumentException
     *
     * @param initialUnit is the initial unit number that could be used by each
     *                    player
     */
    public V1Map12Territories(int initialUnit) { super(initialUnit);}


    /**
     * Helper function to initialize the map structure
     * - all the territories and proper neighboring relationship
     */
    @Override
    protected void initMap() {
        // Creating Terrritores
        Territory rottweiler = new GameTerritory("Rottweiler");
        Territory dachshund = new GameTerritory("Dachshund");
        Territory beagle = new GameTerritory("Beagle");
        Territory labrador = new GameTerritory("Labrador");
        Territory poodle = new GameTerritory("Poodle");
        Territory bulldog = new GameTerritory("Bulldog");
        Territory boxer = new GameTerritory("Boxer");
        Territory havanese = new GameTerritory("Havanese");
        Territory spaniel = new GameTerritory("Spaniel");
        Territory sheepdog = new GameTerritory("Sheepdog");
        Territory akita = new GameTerritory("Akita");
        Territory brittany = new GameTerritory("Brittany");

        addTerritoriesNeighbours(rottweiler, dachshund);
        addTerritoriesNeighbours(dachshund, beagle);
        addTerritoriesNeighbours(rottweiler, labrador);
        addTerritoriesNeighbours(rottweiler, poodle);
        addTerritoriesNeighbours(dachshund, poodle);
        addTerritoriesNeighbours(dachshund, bulldog);
        addTerritoriesNeighbours(beagle, bulldog);
        addTerritoriesNeighbours(labrador, poodle);
        addTerritoriesNeighbours(poodle, bulldog);
        addTerritoriesNeighbours(labrador, boxer);
        addTerritoriesNeighbours(labrador, havanese);
        addTerritoriesNeighbours(poodle, havanese);
        addTerritoriesNeighbours(poodle, spaniel);
        addTerritoriesNeighbours(bulldog, spaniel);
        addTerritoriesNeighbours(boxer, havanese);
        addTerritoriesNeighbours(havanese, spaniel);
        addTerritoriesNeighbours(boxer, sheepdog);
        addTerritoriesNeighbours(sheepdog, havanese);
        addTerritoriesNeighbours(havanese, akita);
        addTerritoriesNeighbours(akita, spaniel);
        addTerritoriesNeighbours(spaniel, brittany);
        addTerritoriesNeighbours(sheepdog, akita);
        addTerritoriesNeighbours(akita, brittany);

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
    }
}
