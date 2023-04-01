package edu.duke.ece651.team13.server.service;

import edu.duke.ece651.team13.server.entity.MapEntity;
import edu.duke.ece651.team13.server.entity.PlayerEntity;
import edu.duke.ece651.team13.server.entity.TerritoryEntity;

public interface TerritoryService {

    TerritoryEntity createTerritory(String name, int unitNo, MapEntity map, PlayerEntity player);

    TerritoryEntity getTerritoriesByMap(Long Id);

    TerritoryEntity updateTerritory(Long Id, PlayerEntity owner, int unit);

    void addNeighbour(TerritoryEntity territory1, TerritoryEntity territory2, Integer distance);

}
