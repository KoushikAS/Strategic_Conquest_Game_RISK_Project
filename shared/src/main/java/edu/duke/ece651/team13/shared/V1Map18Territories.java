package edu.duke.ece651.team13.shared;

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
        Territory vizsla = new GameTerritory("Vizsla");
        Territory pug = new GameTerritory("Pug");
        Territory chihuahua = new GameTerritory("Chihuahua");
        Territory maltese = new GameTerritory("Maltese");
        Territory mastiff = new GameTerritory("Mastiff");
        Territory collie = new GameTerritory("Collie");

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
}
