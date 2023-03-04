package edu.duke.ece651.team13.shared;

import java.util.ArrayList;
import java.util.Map;

/**
 * The interface of Territory
 */
public interface Territory {
    /**
     * Get the id of the territory
     * @return the integer id
     */
    int getId();

    /**
     * Get the name of the territory
     * @return the String name
     */
    String getName();

    /**
     * Get the owner
     * @return the Player that is currently the owner of the territory
     */
    Player getOwner();

    /**
     * Get the number of units that are currently in the territory
     * @return the integer unitNum
     */
    int getUnitNum();

    /**
     * Get the temporary number of units in the territory
     * This is used in the verification of orders
     * @return the integer tempUnitNum
     */
    int getTempUnitNum();

    /**
     * Add a neighbor to the territory
     */
    void addNeighbor(Territory toAdd);

    /**
     * Get the neighbors of the territory
     * @return the arraylist of neighboring territories
     */
    ArrayList<Territory> getNeighbors();

    /**
     * Set the owner
     * @param newOwner is the new owner
     */
    void setOwner(Player newOwner);

    /**
     * Set the unit number
     * @param newUnitNum is the new unitNum
     */
    void setUnitNum(int newUnitNum);

    /**
     * Set the temporary unit number
     * @param newTempUnitNum is the new tempUnitNum
     */
    void setTempUnitNum(int newTempUnitNum);

    /**
     * Rollback the tempUnitNum to be equal to unitNum
     */
    void rollbackTempUnitNum();

    /**
     * Commit the temporary unit number, set the unitNum to be the same as tempUnitNum
     */
    void commitTempUnitNum();

    /**
     * Add an attacker to the attacker list of this turn
     * @param attacker is the attacking player
     * @param attackUnitNum is the number of attacking units
     */
    void addAttacker(Player attacker, int attackUnitNum);

    /**
     * Remove all the attackers
     */
    void clearAttackers();

    /**
     * Get the attackers
     * @return the map of attacking players to the attacking unit numbers
     */
    Map<Player, Integer> getAttackers();
}
