package edu.duke.ece651.team13.server;

import java.net.Socket;

/**
 * The interface of Game
 */
public interface Game {

    void initGame();

    void initPlayer(String name, Socket clientSocket);

    /**
     * Get the number of players
     * @return int number
     */
    int getMaxPlayers();

    /**
     * Get the board of one game
     * @return Board game board
     */
    Board getBoard();

    void playOneTurn();

}