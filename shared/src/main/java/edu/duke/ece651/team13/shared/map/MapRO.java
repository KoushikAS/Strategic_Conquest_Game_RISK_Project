package edu.duke.ece651.team13.shared.map;

import edu.duke.ece651.team13.shared.territory.Territory;
import edu.duke.ece651.team13.shared.territory.TerritoryRO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * This interface handles the graph structure in the map
 */
public interface MapRO {


  /**
   * Get the iterator list of territories
   */
  Iterator<TerritoryRO> getTerritoriesROIterator();

  /**
   * Get the territory by its name
   * @return the territory with the corresponding name
   *         null if the territory is not found
   */
  Territory getTerritoryByName(String name);

  /**
   * Make a deep copy of the map
   * @return the deep copy of the map
   */
  MapRO replicate();

  /**
   * Get the list of iterators for all groups (2 groups, 3 groups or 4 groups)
   */
  ArrayList<Iterator<Territory>> getInitialGroups();
}
