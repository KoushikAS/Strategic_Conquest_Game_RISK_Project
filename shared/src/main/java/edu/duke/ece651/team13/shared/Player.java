package edu.duke.ece651.team13.shared;


/**
 * The interface of player
 */
public interface Player {
    //TODO: playOneTurn and checkIsLost


    /**
     * Get the name of the player
     *
     * @return the string name of the player
     */
    String getName();

    /**
     * Get the status of the player
     *
     * @return the string status of the player
     */
    PlayerStatusEnum getStatus();

    /**
     * Set the status of the player
     * precondition: the status needs to be valid (isValidStatus returns true)
     *
     * @param status is the status string to set
     */
    void setStatus(PlayerStatusEnum status);


}
