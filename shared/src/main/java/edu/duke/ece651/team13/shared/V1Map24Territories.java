package edu.duke.ece651.team13.shared;

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
        Territory dalmatian = new GameTerritory("Dalmatian");
        Territory papillon = new GameTerritory("Papillon");
        Territory setter  = new GameTerritory("Setter");
        Territory samoyed = new GameTerritory("Samoyed");
        Territory bullmastiff = new GameTerritory("Bullmastiff");
        Territory whippet = new GameTerritory("Whippet");

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
}
