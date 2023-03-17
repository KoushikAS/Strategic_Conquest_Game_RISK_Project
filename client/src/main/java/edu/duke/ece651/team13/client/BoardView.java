package edu.duke.ece651.team13.client;

import edu.duke.ece651.team13.shared.map.MapRO;
import edu.duke.ece651.team13.shared.territory.Territory;
import edu.duke.ece651.team13.shared.territory.TerritoryRO;

/**
 * This interface handles a view of the board
 */
public interface BoardView {
    /**
     * This method displays the board
     * @return the text to be displayed in string
     */
    String displayAllTerritories(MapRO map);

    /**
     * This method displays the territories grouped by owner
     * @return the text to be displayed in string
     */
    String displayTerritoriesOfOwner(MapRO map, String ownerName);

    /**
     * This method only display one territory
     * @param t the territory to be displayed
     * @return the text to be displayed in string
     */
    String displayOneTerritory(TerritoryRO t);
}
