package edu.duke.ece651.team13.server.service;

import edu.duke.ece651.team13.shared.player.PlayerRO;
import edu.duke.ece651.team13.shared.territory.TerritoryRO;

/**
 * The interface of Territory
 */
public interface TerritoryService extends TerritoryROService {

  /**
   * Set the owner
   * 
   * @param newOwner is the new owner
   */
  void setOwner(Long TerritoryId, PlayerRO newOwner);

  /**
   * Set the unit number
   * 
   * @param newUnitNum is the new unitNum
   */
  void setUnitNum(Long TerritoryId, int newUnitNum);

  /**
   * Add an attacker to the attacker list of this turn
   * 
   * @param attacker      is the attacking player
   * @param attackUnitNum is the number of attacking units
   */
  void addAttacker(Long TerritoryId, PlayerRO attacker, int attackUnitNum);

  /**
   * Remove all the attackers
   */
  void clearAttackers(Long TerritoryId);

  /**
   * add Neighbouring territores iterator.
   */
  void addNeighbours(Long TerritoryId, TerritoryRO neighbour);

}
