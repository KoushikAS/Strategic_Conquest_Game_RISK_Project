package edu.duke.ece651.team13.client;

import edu.duke.ece651.team13.shared.map.MapRO;
import edu.duke.ece651.team13.shared.territory.Territory;
import edu.duke.ece651.team13.shared.map.V1Map;
import edu.duke.ece651.team13.shared.territory.TerritoryRO;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * This class is a text view representation of the RISC game board
 */
public class BoardTextView implements BoardView {

  private MapRO map;
  private Map<String, ArrayList<Territory>> ownershipMap;

  /**
   * Constructs a BoardTextView instance
   * 
   * @param map the map to be displayed in the view
   */
  public BoardTextView(MapRO map) {
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
    String[] tempOwnerNames = { "Blue", "Green", "Red" };
    int tempIdx = 0;
    Iterator<Territory> it = map.getTerritoriesIterator();
    while (it.hasNext()) {
      // TODO: hack to get owner name bc owners are not initialized in map now
      Territory t = it.next();
      String ownerName = t.getOwner() == null ? tempOwnerNames[tempIdx % 3] : t.getOwner().getName();
      tempIdx++;
      ownershipMap.putIfAbsent(ownerName, new ArrayList<>());
      ownershipMap.get(ownerName).add(t);
    }
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
    while(it.hasNext()){
      TerritoryRO neighbour = it.next();
      sb.append(neighbour.getName());
      if(it.hasNext()){
        sb.append(", ");
      }
    }
    sb.append(")\n");
    return sb.toString();
  }

}
