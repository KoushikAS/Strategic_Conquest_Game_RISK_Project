package edu.duke.ece651.team13.shared.player;

import edu.duke.ece651.team13.shared.enums.PlayerStatusEnum;

import java.io.Serializable;
import java.net.Socket;

/**
 * This class handles the information of one human player
 */
public class HumanPlayer implements Player, Serializable {

    private final String name;
    private PlayerStatusEnum status;
    private transient Socket socket;

    /**
     * Construct a new Player
     */
    public HumanPlayer(String name) {
        this.name = name;
        this.status = PlayerStatusEnum.PLAYING;
        this.socket = null;
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

    /**
     * Get Status of the player.
     *
     * @return the status of the player.
     */
    @Override
    public PlayerStatusEnum getStatus() {
        return status;
    }

    /**
     * Get the socket of the client linked with the player.
     *
     * @return socket of the client linked with the player
     */
    @Override
    public Socket getSocket() {
        return socket;
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

    /**
     * Set Socket from the client for the player.
     *
     * @param socket to be set for the player.
     */
    @Override
    public void setSocket(Socket socket) {
        this.socket = socket;
    }

}
