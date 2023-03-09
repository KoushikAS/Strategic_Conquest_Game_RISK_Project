package edu.duke.ece651.team13.shared;

/**
 * The interface of Game
 */
public interface Game {

    void initGame();

    void initPlayer();

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