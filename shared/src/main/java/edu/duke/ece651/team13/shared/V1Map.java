package edu.duke.ece651.team13.shared;

import java.net.Socket;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class V1Map implements Map, Serializable {
  private ArrayList<Territory> territories;
  private final int initialUnit;

  @Override
  public int getInitialUnit() {
    return initialUnit;
  }

  @Override
  public Iterator<Territory> getTerritoriesIterator() {
    return territories.iterator();
  }

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
    this.initialUnit = initialUnit;
    initMap();
    // TODO: validate connected graph
  }

  @Override
  // todo: More conditions are needed to determine that two maps are equal
  public boolean equals(Object other) {
    if (other != null && other.getClass().equals(getClass())) {
      V1Map otherV1Map = (V1Map) other;
      return otherV1Map.getInitialUnit() == this.getInitialUnit();
    }
    return false;
  }

  /**
   * HelperFunction to add to neighbourlist of territory
   * pre condition : Both territory cannot be same
   * 
   * @param territory t1
   * @param territory t2
   **/
  private void addTerritoriesNeighbours(Territory t1, Territory t2) {
    t1.addNeighbours(t2);
    t2.addNeighbours(t1);
  }

  /**
   * Helper function to initialize the map structure
   * - all the territories and proper neighboring relationship
   */
  private void initMap() {
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

}
