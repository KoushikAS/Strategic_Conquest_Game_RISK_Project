package edu.duke.ece651.team13.server;

import edu.duke.ece651.team13.shared.map.Map;

/**
 * Board interface
 *
 * @param <T> indicates the type of view (e.g. Character for text view)
 */
public interface Board <T> {
    /**
     * Get the map of this game board
     */
    Map getMap();
}
