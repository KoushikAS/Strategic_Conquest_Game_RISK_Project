package edu.duke.ece651.team13.server;

import edu.duke.ece651.team13.shared.map.MapRO;
import edu.duke.ece651.team13.shared.order.PlayerOrderInput;
import edu.duke.ece651.team13.shared.player.Player;
import edu.duke.ece651.team13.shared.player.PlayerRO;
import edu.duke.ece651.team13.shared.territory.Territory;

import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * The interface of Game
 */
public interface Game {


    void initPlayer(String name, Socket clientSocket);

    Iterator<Player> getPlayersIterator();

    /**
     * Get the Map Read Only object of  game
     *
     * @return Board game board
     */
    MapRO getMapRO();

    void playOneTurn();

    /**
     * Validate a batch of orders made by one player
     *
     * @param orderInputs is the list of orders made by a player
     * @return null if the batch of order is valid,
     * description of error if invalid
     */
     String validateOrdersAndAddToList(ArrayList<PlayerOrderInput> orderInputs, PlayerRO player);

    /**
     * Get the player by the player's name
     *
     * @return the player with the corresponding name if found
     * null if such player is not found
     */
    Player getPlayerByName(String name);

    /**
     * Resolve all the combats in all territories for this turn
     */
    void resolveAllCombats();

    /**
     * Resolve the combat in the specified territory for this turn
     *
     * @param territory is the territory on which to resolve combat
     */
    void resolveCombatInOneTerritory(Territory territory);

    /**
     * This method checks if a player is lost, i.e., he no longer controls
     * any territories
     */
    void checkLostPlayer();

    /**
     * This method checks if game is over (i.e. if there is only one player with Playing status)
     */
    Boolean isGameOver();

    /**
     * This method finds the winning player of the game and returns it
     *
     * @return the winning player
     */
    Player getWinningPlayer();
}