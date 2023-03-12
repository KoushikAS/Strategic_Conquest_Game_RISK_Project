package edu.duke.ece651.team13.shared.territory;

import edu.duke.ece651.team13.shared.Player;

import java.util.Map;
import java.util.Iterator;

/**
 * The interface of Territory
 */
public interface Territory extends TerritoryRO{

  /**
   * Set the owner
   * 
   * @param newOwner is the new owner
   */
  void setOwner(Player newOwner);

  /**
   * Set the unit number
   * 
   * @param newUnitNum is the new unitNum
   */
  void setUnitNum(int newUnitNum);

  /**
   * Add an attacker to the attacker list of this turn
   * 
   * @param attacker      is the attacking player
   * @param attackUnitNum is the number of attacking units
   */
  void addAttacker(Player attacker, int attackUnitNum);

  /**
   * Remove all the attackers
   */
  void clearAttackers();


  /**
   * add Neighbouring territores iterator.
   */
  void addNeighbours(TerritoryRO neighbour);

  /**
   * Make a deep copy of the territory, but will leave out the neighbor information
   */
  Territory replicate();
}
