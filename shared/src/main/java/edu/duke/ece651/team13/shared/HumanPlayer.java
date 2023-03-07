package edu.duke.ece651.team13.shared;

import java.net.Socket;

/**
 * This class handles the information of one human player
 */
public class HumanPlayer implements Player{
    private int id;
    static public int nextId = 0;
    private String name;
    private Socket clientSocket;
    // TODO: Change the status to an enum
    private String status;
    public static final String LOSE_STATUS = "LOSE";
    public static final String PLAYING_STATUS = "PLAYING";

    /**
     * Construct a new Player
     */
    public HumanPlayer(
                       String name,
                       Socket clientSocket){
        this.id = nextId++;
        this.name = name;
        this.clientSocket = clientSocket;
        this.status = getInitStatus();
    }

    /**
     * Get the initial status of player
     * @return the initial status string
     */
    private static String getInitStatus(){
        return PLAYING_STATUS;
    }

    /**
     * Get the id of this player
     * @return the integer id of the player
     */
    @Override
    public int getId() {
        return id;
    }

    /**
     * Get the name of the player
     * @return the string name of the player
     */
    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getStatus() {
        return status;
    }

    /**
     * Set the status of the player
     * precondition: the status needs to be valid (isValidStatus returns true)
     *
     * @param status is the status string to set
     */
    @Override
    public void setStatus(String status) {
        if(!isValidStatus(status)) throw new IllegalArgumentException("The status string is invalid for player");
        this.status = status;
    }

    /**
     * Check if a status string is a valid status
     * @param status is the string to check
     * @return true if valid
     *         false if not
     */
    @Override
    public boolean isValidStatus(String status) {
        return status.equals(LOSE_STATUS) || status.equals(PLAYING_STATUS);
    }

    @Override
    public void setNextIdToZero() {
        nextId = 0;
    }
}
