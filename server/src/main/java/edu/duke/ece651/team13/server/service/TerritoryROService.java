package edu.duke.ece651.team13.server.service;

import edu.duke.ece651.team13.shared.player.PlayerRO;

import java.util.HashMap;
import java.util.Iterator;

/**
 * The interface of Territory
 */
public interface TerritoryROService {

  /**
   * Get the name of the territory
   * 
   * @return the String name
   */
  String getName(Long territoryId);

  /**
   * Get the owner
   * 
   * @return the Player that is currently the owner of the territory
   */
  PlayerRO getOwner(Long territoryId);

  /**
   * Get the number of units that are currently in the territory
   * 
   * @return the integer unitNum
   */
  int getUnitNum(Long territoryId);

  /**
   * Get the attackers
   * 
   * @return the map of attacking players to the attacking unit numbers
   */
  HashMap<PlayerRO, Integer> getAttackers(Long territoryId);

  /**
   * get Neighbouring territories iterator
   */
  Iterator<TerritoryROService> getNeighbourIterartor(Long territoryId);

}
