package edu.duke.ece651.team13.shared;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * This class handles the owner, neighbors, unit numbers
 * and current attackers of the territory
 */
public class GameTerritory implements Territory, Serializable{
    private final int id;
    private final String name;
    private Player owner;
    private int unitNum;
    private int tempUnitNum;
    // TODO: Add neighbors
    private Map<Player, Integer> attackers;

    public GameTerritory(int id, String name){
        this.id = id;
        this.name = name;
        this.owner = null;
        this.unitNum = 0;
        this.tempUnitNum = 0;
        this.attackers = new HashMap<>();
    }

    /**
     * Get the id of the territory
     * @return the integer id
     */
    @Override
    public int getId() {
        return this.id;
    }

    /**
     * Get the name of the territory
     * @return the String name
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Get the owner
     * @return the Player that is currently the owner of the territory
     */
    @Override
    public Player getOwner() {
        return this.owner;
    }

    /**
     * Get the number of units that are currently in the territory
     * @return the integer unitNum
     */
    @Override
    public int getUnitNum() {
        return this.unitNum;
    }

    /**
     * Get the temporary number of units in the territory
     * This is used in the verification of orders
     * @return the integer tempUnitNum
     */
    @Override
    public int getTempUnitNum() {
        return this.tempUnitNum;
    }

    /**
     * Set the owner
     * @param newOwner is the new owner
     */
    @Override
    public void setOwner(Player newOwner) {
        this.owner = newOwner;
    }

    /**
     * Set the unit number
     * precondition: newUnitNum must be >= 0, if not, throws IllegalArgumentException
     * @param newUnitNum is the new unitNum
     */
    @Override
    public void setUnitNum(int newUnitNum) {
        if(newUnitNum < 0)
            throw new IllegalArgumentException("The unit number in a territory should be >= 0.");
        this.unitNum = newUnitNum;
    }

    /**
     * Set the temporary unit number
     * precondition: newTempUnitNum must be >= 0, if not, throws IllegalArgumentException
     * @param newTempUnitNum is the new tempUnitNum
     */
    @Override
    public void setTempUnitNum(int newTempUnitNum) {
        if(newTempUnitNum < 0)
            throw new IllegalArgumentException("The unit number in a territory should be >= 0.");
        this.tempUnitNum = newTempUnitNum;
    }

    /**
     * Commit the temporary unit number, set the unitNum to be the same as tempUnitNum
     */
    @Override
    public void commitTempUnitNum(){
        setUnitNum(tempUnitNum);
    }

    /**
     * Rollback the tempUnitNum to be equal to unitNum
     */
    @Override
    public void rollbackTempUnitNum() {
        setTempUnitNum(unitNum);
    }

    /**
     * Add an attacker to the attacker list of this turn
     * @param attacker is the attacking player
     * @param attackUnitNum is the number of attacking units
     */
    @Override
    public void addAttacker(Player attacker, int attackUnitNum) {
        this.attackers.put(attacker, attackUnitNum);
    }

    /**
     * Remove all the attackers
     */
    @Override
    public void clearAttackers() {
        this.attackers.clear();
    }

    /**
     * Get the attackers
     * @return the map of attacking players to the attacking unit numbers
     */
    @Override
    public Map<Player, Integer> getAttackers() {
        return this.attackers;
    }
}
