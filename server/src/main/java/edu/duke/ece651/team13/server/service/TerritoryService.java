package edu.duke.ece651.team13.server.service;

import edu.duke.ece651.team13.server.entity.MapEntity;
import edu.duke.ece651.team13.server.entity.PlayerEntity;
import edu.duke.ece651.team13.server.entity.TerritoryEntity;
import edu.duke.ece651.team13.server.entity.UnitEntity;
import edu.duke.ece651.team13.shared.territory.Territory;

import java.util.List;

public interface TerritoryService {

    TerritoryEntity createTerritory(String name, MapEntity map, PlayerEntity player, int foodProduction, int techProduction);

    TerritoryEntity getTerritoriesById(Long Id);

    List<TerritoryEntity> getTerritoriesByPlayer(PlayerEntity player);

    TerritoryEntity updateTerritoryOwner(TerritoryEntity territory, PlayerEntity owner);

    void addNeighbour(TerritoryEntity territory1, TerritoryEntity territory2, Integer distance);

    TerritoryEntity updateTerritoryUnits(TerritoryEntity territory, List<UnitEntity> units);
}
