package edu.duke.ece651.team13.shared.player;


import edu.duke.ece651.team13.shared.enums.PlayerStatusEnum;

import java.net.Socket;

/**
 * The interface of player
 */
public interface Player extends PlayerRO {
    //TODO: playOneTurn and checkIsLost

    /**
     * Set the status of the player
     * precondition: the status needs to be valid (isValidStatus returns true)
     *
     * @param status is the status string to set
     */
    void setStatus(PlayerStatusEnum status);

    /**
     * Set Socket from the client for the player.
     *
     * @param socket to be set for the player.
     */
     void setSocket(Socket socket);


}
