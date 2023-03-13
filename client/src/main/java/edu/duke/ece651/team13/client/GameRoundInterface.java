package edu.duke.ece651.team13.client;

import edu.duke.ece651.team13.shared.order.PlayerOrderInput;

import java.io.IOException;
import java.util.ArrayList;

/**
 * The interface for each round of the game
 */
public interface GameRoundInterface {

    /**
     * This method lets the player plays one turn of the game
     * and Returns the orders played by the player.
     *
     * @throws IOException
     */
    ArrayList<PlayerOrderInput> playOneRound() throws IOException;

}
