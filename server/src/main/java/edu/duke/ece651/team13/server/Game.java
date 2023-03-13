package edu.duke.ece651.team13.server;

import edu.duke.ece651.team13.shared.Player;
import edu.duke.ece651.team13.shared.order.Order;
import edu.duke.ece651.team13.shared.territory.Territory;

import java.util.ArrayList;
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

    /**
     * Validate a batch of orders made by one player
     *
     * @param orders is the list of orders made by a player
     * @return null if the batch of order is valid,
     *         description of error if invalid
     */
    String validateOrders(ArrayList<Order> orders);

    /**
     * Get the player by the player's name
     * @return the player with the corresponding name if found
     *         null if such player is not found
     */
    Player getPlayerByName(String name);

    /**
     * Resolve all the combats in all territories for this turn
     */
    public void resolveAllCombats();

    /**
     * Resolve the combat in the specified territory for this turn
     *
     * @param territory is the territory on which to resolve combat
     */
    public void resolveCombatInOneTerritory(Territory territory);
}