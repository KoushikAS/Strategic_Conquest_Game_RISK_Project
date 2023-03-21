package edu.duke.ece651.team13.shared.util;

import edu.duke.ece651.team13.shared.map.MapRO;
import edu.duke.ece651.team13.shared.territory.Territory;

import java.util.Iterator;

public class mapUtil {

    private mapUtil(){}


    /**
     * This helper method checks if a player has lost
     *
     * @param map is the map we need to check.
     * @param player is the player to be checked
     * @return true if the player has already lost and false otherwise
     */
    public static boolean isPlayerLost(MapRO map,  String player) {
        Iterator<Territory> territoryIterator = map.getTerritoriesIterator();
        while (territoryIterator.hasNext()) {
            Territory territory = territoryIterator.next();
            if (territory.getOwner().getName().equals(player)) {
                return false;
            }
        }
        return true;
    }

}
