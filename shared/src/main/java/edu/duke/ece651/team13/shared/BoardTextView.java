package edu.duke.ece651.team13.shared;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
 * This class is a text view representation of the RISC game board
 */
public class BoardTextView implements BoardView {

    private V1Map map;
    private Map<String, ArrayList<Territory>> ownershipMap;

    /**
     * Constructs a BoardTextView instance
     * @param map the map to be displayed in the view
     */
    public BoardTextView(V1Map map) {
        this.map = map;
        this.ownershipMap = new TreeMap<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String display() {
        return displayTerritories();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String displayTerritories() {
        groupTerritoriesByOwner();
        StringBuffer sb = new StringBuffer();
        for (String ownerName : ownershipMap.keySet()) {
            sb.append(ownerName + " player:\n");
            sb.append("-------------\n");
            for (Territory t : ownershipMap.get(ownerName)) {
                sb.append(displayOneTerritory(t));
            }
        }
        return sb.toString();
    }

    /**
     * This method groups all the territories in map into a TreeMap
     */
    private void groupTerritoriesByOwner() {
        String[] tempOwnerNames = {"Blue", "Green", "Red"};
        int tempIdx = 0;
        for (Territory t : map.getTerritories()) {
            // TODO: hack to get owner name bc owners are not initialized in map now
            String ownerName = t.getOwner() == null ? tempOwnerNames[tempIdx] : t.getOwner().getName();
            tempIdx++;
            ownershipMap.putIfAbsent(ownerName, new ArrayList<>());
            ownershipMap.get(ownerName).add(t);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String displayOneTerritory(Territory t) {
        StringBuffer sb = new StringBuffer();
        int unitNum = t.getUnitNum();
        String name = t.getName();
        sb.append(unitNum + " units in " + name + "\n");
        return sb.toString();
    }

}
