package edu.duke.ece651.team13.server.service;

import edu.duke.ece651.team13.server.entity.MapEntity;
import edu.duke.ece651.team13.server.entity.PlayerEntity;
import edu.duke.ece651.team13.server.entity.TerritoryEntity;

public interface TerritoryService {

    TerritoryEntity createTerritory(String name, MapEntity map, PlayerEntity player, int foodProduction, int techProduction);

    TerritoryEntity getTerritoriesByMap(Long Id);

    TerritoryEntity updateTerritoryOwner(Long Id, PlayerEntity owner);

    void addNeighbour(TerritoryEntity territory1, TerritoryEntity territory2, Integer distance);

}
