package edu.duke.ece651.team13.client;

import java.io.IOException;

/**
 * The interface for each round of the game
 */
public interface GameRoundInterface {

    /**
     * This method lets the player plays one turn of the game
     *
     * @throws IOException
     */
    void playOneRound() throws IOException;

}
