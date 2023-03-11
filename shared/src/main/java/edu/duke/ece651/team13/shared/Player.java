package edu.duke.ece651.team13.shared;

import java.io.IOException;

/**
 * The interface of player
 */
public interface Player {
    //TODO: playOneTurn and checkIsLost

    /**
     * Get the id of this player
     * @return the integer id of the player
     */
    int getId();

    /**
     * Get the name of the player
     * @return the string name of the player
     */
    String getName();

    /**
     * Get the status of the player
     * @return the string status of the player
     */
    String getStatus();

    /**
     * Set the status of the player
     * precondition: the status needs to be valid (isValidStatus returns true)
     *
     * @param status is the status string to set
     */
    void setStatus(String status);

    /**
     * Check if a status string is a valid status
     * @param status is the string to check
     * @return true if valid
     *         false if not
     */
    boolean isValidStatus(String status);

    /**
     * Set the static nextId field back to zero
     */
    void setNextIdToZero();

    /**
     * This method lets the player plays one turn of the game
     *
     * @throws IOException
     */
    void playOneTurn() throws IOException;

    /**
     * This method let the player do unit placements into their territories
     */
    void placeUnits();
}
