package edu.duke.ece651.team13.shared;

import java.io.BufferedReader;
import java.io.Serializable;

/**
 * This class handles the information of one human player
 */
public class HumanPlayer implements Player, Serializable {

    private final String name;
    private PlayerStatusEnum status;

    /**
     * Construct a new Player
     */
    public HumanPlayer(String name, BufferedReader inputReader) {
        this.name = name;
        this.status = PlayerStatusEnum.PLAYING;

    }

    /**
     * Get the name of the player
     *
     * @return the string name of the player
     */
    @Override
    public String getName() {
        return name;
    }

    @Override
    public PlayerStatusEnum getStatus() {
        return status;
    }

    /**
     * Set the status of the player
     * precondition: the status needs to be valid (isValidStatus returns true)
     *
     * @param status is the status string to set
     */
    @Override
    public void setStatus(PlayerStatusEnum status) {
        this.status = status;
    }


}
