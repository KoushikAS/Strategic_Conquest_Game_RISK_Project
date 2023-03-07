package edu.duke.ece651.team13.shared;

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
