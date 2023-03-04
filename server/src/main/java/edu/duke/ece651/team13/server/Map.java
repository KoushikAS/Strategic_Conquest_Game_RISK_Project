package edu.duke.ece651.team13.server;


/**
 * This interface handles the graph structure in the map
 */
public interface Map{
    /**
     * Get the initial unit number that each player could have
     */
    int getInitialUnit();

//    /**
//     * Given all the players in the game, assign a group of territories to each player.
//     * Each player will be the owner of a group of territories after calling the method.
//     * Precondition: the length of players must be between 2 and 4, inclusive
//     *
//     * @param players are the players
//     */
//    void assignTerritories(ArrayList<Player> players);

//    /**
//     * This initializes all the territories in the map, set up their neighboring structure
//     */
//    void initMap();
}
