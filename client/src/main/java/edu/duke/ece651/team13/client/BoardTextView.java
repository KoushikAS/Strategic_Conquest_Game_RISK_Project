package edu.duke.ece651.team13.client;

import edu.duke.ece651.team13.shared.map.MapRO;
import edu.duke.ece651.team13.shared.territory.Territory;
import edu.duke.ece651.team13.shared.territory.TerritoryRO;

import java.util.*;

/**
 * This class is a text view representation of the RISC game board
 */
public class BoardTextView implements BoardView {


    /**
     * Constructs a BoardTextView instance
     *
     */
    public BoardTextView() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String displayAllTerritories(MapRO map) {
        StringBuffer sb = new StringBuffer();
        Iterator<String> playerIterator = getPlayerNames(map);
        while (playerIterator.hasNext()) {
            String ownerName = playerIterator.next();
            sb.append(ownerName + " player:\n");
            sb.append("-------------\n");
            sb.append(displayTerritoriesOfOwner(map, ownerName));
        }

        return sb.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String displayTerritoriesOfOwner(MapRO map, String ownerName) {
        Iterator<TerritoryRO> territoryIterator = getTerritoryOfOwner(map, ownerName);
        StringBuffer sb = new StringBuffer();
        while (territoryIterator.hasNext()) {
            sb.append(displayOneTerritory(territoryIterator.next()));
        }
        return sb.toString();
    }

    private Iterator<String> getPlayerNames(MapRO map) {
        Set<String> names = new HashSet<>();

        Iterator<TerritoryRO> it = map.getTerritoriesROIterator();
        while (it.hasNext()) {
            TerritoryRO territoryRO = it.next();
            names.add(territoryRO.getOwner().getName());
        }
        return names.iterator();
    }

    /**
     * This method groups all the territories in map into a TreeMap
     */
    private Iterator<TerritoryRO> getTerritoryOfOwner(MapRO map, String ownerName) {
        Iterator<TerritoryRO> it = map.getTerritoriesROIterator();
        ArrayList<TerritoryRO> territories = new ArrayList<>();

        while (it.hasNext()) {
            TerritoryRO t = it.next();
            if (t.getOwner().getName().equals(ownerName)) {
                territories.add(t);
            }
        }

        return territories.iterator();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String displayOneTerritory(TerritoryRO t) {
        StringBuffer sb = new StringBuffer();
        int unitNum = t.getUnitNum();
        String name = t.getName();
        sb.append(unitNum + " units in " + name + " (next to: ");
        Iterator<TerritoryRO> it = t.getNeighbourIterartor();
        while (it.hasNext()) {
            TerritoryRO neighbour = it.next();
            sb.append(neighbour.getName());
            if (it.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append(")\n");
        return sb.toString();
    }

}
