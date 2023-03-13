package edu.duke.ece651.team13.shared.territory;

import edu.duke.ece651.team13.shared.Player;

import java.util.HashMap;
import java.util.Iterator;

/**
 * The interface of Territory
 */
public interface TerritoryRO {

  /**
   * Get the name of the territory
   * 
   * @return the String name
   */
  String getName();

  /**
   * Get the owner
   * 
   * @return the Player that is currently the owner of the territory
   */
  Player getOwner();

  /**
   * Get the number of units that are currently in the territory
   * 
   * @return the integer unitNum
   */
  int getUnitNum();

  /**
   * Get the attackers
   * 
   * @return the map of attacking players to the attacking unit numbers
   */
  HashMap<Player, Integer> getAttackers();

  /**
   * get Neighbouring territories iterator
   */
  Iterator<TerritoryRO> getNeighbourIterartor();

}
