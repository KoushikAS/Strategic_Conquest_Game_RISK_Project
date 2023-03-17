package edu.duke.ece651.team13.shared.player;


import edu.duke.ece651.team13.shared.enums.PlayerStatusEnum;

import java.net.Socket;

/**
 * The interface of player
 */
public interface PlayerRO {

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
     * Get the socket of the client linked with the player.
     *
     * @return socket of the client linked with the player
     */
     Socket getSocket();

}
