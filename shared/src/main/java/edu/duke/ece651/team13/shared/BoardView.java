package edu.duke.ece651.team13.shared;

/**
 * This interface handles a view of the board
 */
public interface BoardView {
    /**
     * This method displays the board
     * @return the text to be displayed in string
     */
    String display();

    /**
     * This method displays the territories grouped by owner
     * @return the text to be displayed in string
     */
    String displayTerritories();

    /**
     * This method only display one territory
     * @param t the territory to be displayed
     * @return the text to be displayed in string
     */
    String displayOneTerritory(Territory t);
}
