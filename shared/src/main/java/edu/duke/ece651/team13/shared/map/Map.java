package edu.duke.ece651.team13.shared.map;

import edu.duke.ece651.team13.shared.Territory;

import java.util.Iterator;

/**
 * This interface handles the graph structure in the map
 */
public interface Map {
  /**
   * Get the initial unit number that each player could have
   */
  int getInitialUnit();

  /**
   * Get the iterator list of territories
   */
  Iterator<Territory> getTerritoriesIterator();

  /**
   * Check if the map is a connected graph
   *
   * @return true if connected, false if not
   */
  boolean isConnected();
}
