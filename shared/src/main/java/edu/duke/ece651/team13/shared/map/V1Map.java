package edu.duke.ece651.team13.shared.map;

import edu.duke.ece651.team13.shared.Territory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public abstract class V1Map implements Map, Serializable {
  protected ArrayList<Territory> territories;
  protected HashMap<Integer, Territory> territoryIDMap;
  private final int initialUnit;

  /**
   * Construct the V1Map
   * Precondition: initialUnit > 0, or throw IllegalArgumentException
   *
   * @param initialUnit is the initial unit number that could be used by each
   *                    player
   */
  public V1Map(int initialUnit) {
    if (initialUnit <= 0)
      throw new IllegalArgumentException("The initialUnit must be >0");
    this.territories = new ArrayList<>();
    this.initialUnit = initialUnit;
    initMap();
    assert(isConnected());
    this.territoryIDMap = new HashMap<>();
    for(Territory t: territories){
      territoryIDMap.put(t.getId(), t);
    }
  }

  public V1Map(V1Map toCopy){
    territories = new ArrayList<>();
    territoryIDMap = new HashMap<>();
    for(Territory t: toCopy.territories){
      Territory cloneT = t.replicate();
      territories.add(cloneT);
      territoryIDMap.put(t.getId(), cloneT);
    }
    for(Territory t: toCopy.territories){
      Territory cloneT = getTerritoryByID(t.getId());
      for (Iterator<Territory> it = t.getNeighbourIterartor(); it.hasNext(); ) {
        int neighborID = it.next().getId();
        Territory cloneNeighbor = getTerritoryByID(neighborID);
        cloneT.addNeighbours(cloneNeighbor);
      }
    }
    initialUnit = toCopy.initialUnit;
  }

  protected ArrayList<ArrayList<Territory>> groups; //list of each group's territories

  @Override
  public int getInitialUnit() {
    return initialUnit;
  }

  @Override
  public Iterator<Territory> getTerritoriesIterator() {
    return territories.iterator();
  }

  @Override
  public boolean isConnected() {
    // Take a boolean visited array, boolean default to false
    HashSet<Integer> visited = new HashSet<>();

    // Start the DFS from vertex 0
    DFS(territories.get(0), visited);

    // Check if all the vertices are visited
    for (Territory t: territories) {
      if (!visited.contains(t.getId())) {
        return false;
      }
    }
    return true;
  }

  /**
   * Do a DFS from the source territory, mark each visited territory
   * as true in the visited boolean array
   *
   * @param visited is the HashSet to track the visited territories
   */
  private void DFS(Territory source, HashSet<Integer> visited){
    visited.add(source.getId());
    Iterator<Territory> it = source.getNeighbourIterartor();
    while(it.hasNext()){
      Territory neighbor = it.next();
      if(!visited.contains(neighbor.getId())){
        DFS(neighbor, visited);
      }
    }
  }

  @Override
  // todo: More conditions are needed to determine that two maps are equal
  public boolean equals(Object other) {
    if (other != null && other.getClass().equals(getClass())) {
      V1Map otherV1Map = (V1Map) other;
      return otherV1Map.getInitialUnit() == this.getInitialUnit();
    }
    return false;
  }

  /**
   * HelperFunction to add to neighbourlist of territory
   * pre condition : Both territory cannot be same
   * 
   * @param t1: territory t1
   * @param t2: territory t2
   **/
  protected void addTerritoriesNeighbours(Territory t1, Territory t2) {
    t1.addNeighbours(t2);
    t2.addNeighbours(t1);
  }

  /**
   * Helper function to initialize the map structure
   * - all the territories and proper neighboring relationship
   */
  protected abstract void initMap();

  @Override
  public Territory getTerritoryByID(int id){
    return territoryIDMap.get(id);
  }

  /**
   * Get the list of iterators for all groups (2 groups, 3 groups or 4 groups)
   */
  @Override
  public ArrayList<Iterator<Territory>> getGroupsIterator(){
    ArrayList<Iterator<Territory>> groupsIteratorList = new ArrayList<>();
    for (ArrayList<Territory> group : groups) {
      groupsIteratorList.add(group.iterator());
    }
    return groupsIteratorList;
  }
}
