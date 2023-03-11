package edu.duke.ece651.team13.shared;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.Socket;

/**
 * This class handles the information of one human player
 */
public class HumanPlayer implements Player, Serializable {
    private int id;
    static public int nextId = 0;
    private String name;
    private Socket clientSocket;
    // TODO: Change the status to an enum
    private String status;
    private BufferedReader inputReader;
    public static final String LOSE_STATUS = "LOSE";
    public static final String PLAYING_STATUS = "PLAYING";
    // WIN

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
        inputReader = new BufferedReader(new InputStreamReader(System.in));
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

    /**
     * {@inheritDoc}
     */
    @Override
    public void playOneTurn() throws IOException {
        String order = chooseOrder();
        if (order.equals("M")) {
            System.out.println(name + " is performing a Move order...");
        } else if (order.equals("A")) {
            System.out.println(name + " is performing an Attack order...");
        } else {
            System.out.println(name + " is done with this turn.");
        }
    }

    /**
     * This helper method chooses an order from the player
     * @return a String of the player's order
     * @throws IOException
     */
    private String chooseOrder() throws IOException {
        String prompt = "You are the " + name + " player, what would you like to do?\n" +
                "(M)ove\n" +
                "(A)ttack\n" +
                "(D)one";
        String order;
        while (true) {
            order = readOrder(prompt);
            if (order != null) break;
        }
        return order;
    }

    /**
     * This helper method reads an order from the player's input
     * @param prompt the prompt message to guide the player's input
     * @return a String of the player's order
     * @throws IOException
     */
    private String readOrder(String prompt) throws IOException {
        System.out.println(prompt);
        String s = inputReader.readLine().toUpperCase();
        if (s.equals("M") || s.equals("A") || s.equals("D")) {
            return s;
        } else {
            System.out.println("That is not a valid choice");
            return null;
        }
    }

    /**
     * Set the input reader for the player
     * TODO: probably include in the constructor
     *
     * @param reader
     */
    public void setInputReader(BufferedReader reader) {
        this.inputReader = reader;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void placeUnits() {

    }


}
