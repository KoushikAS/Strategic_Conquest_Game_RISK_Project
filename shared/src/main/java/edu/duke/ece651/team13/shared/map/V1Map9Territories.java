package edu.duke.ece651.team13.shared.map;

import edu.duke.ece651.team13.shared.GameTerritory;
import edu.duke.ece651.team13.shared.Territory;

public class V1Map9Territories extends V1Map  {

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
  private V1Map9Territories(V1Map9Territories toCopy){
    super(toCopy);
  }

  /**
   * Helper function to initialize the map structure
   * - all the territories and proper neighboring relationship
   */
  protected void initMap() {
    // Creating Terrritores
    Territory narnia = new GameTerritory("Narnia");
    Territory midkemia = new GameTerritory("Midkemia");
    Territory oz = new GameTerritory("Oz");
    Territory gondor = new GameTerritory("Gondor");
    Territory elantris = new GameTerritory("Elantris");
    Territory scadrial = new GameTerritory("Scadrial");
    Territory roshar = new GameTerritory("Roshar");
    Territory hogwarts = new GameTerritory("Hogwarts");
    Territory mordor = new GameTerritory("Mordor");

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
  public Map replicate() {
    return new V1Map9Territories(this);
  }
}
