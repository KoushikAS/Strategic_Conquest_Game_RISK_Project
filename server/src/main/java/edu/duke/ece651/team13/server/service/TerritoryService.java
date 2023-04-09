package edu.duke.ece651.team13.server.service;

import edu.duke.ece651.team13.server.entity.MapEntity;
import edu.duke.ece651.team13.server.entity.PlayerEntity;
import edu.duke.ece651.team13.server.entity.TerritoryEntity;
import edu.duke.ece651.team13.server.entity.UnitEntity;
import edu.duke.ece651.team13.server.enums.UnitMappingEnum;

import java.util.List;
import java.util.Optional;

public interface TerritoryService {

    TerritoryEntity createTerritory(String name, MapEntity map, PlayerEntity player, int foodProduction, int techProduction);

    TerritoryEntity getTerritoriesById(Long Id);

    List<TerritoryEntity> getTerritoriesByPlayer(PlayerEntity player);

    TerritoryEntity updateTerritoryOwner(TerritoryEntity territory, PlayerEntity owner);

    void addNeighbour(TerritoryEntity territory1, TerritoryEntity territory2, Integer distance);

    TerritoryEntity updateTerritoryUnits(TerritoryEntity territory, List<UnitEntity> units);

    /**
     * This is a helper function that gets the UnitEntity with the specified unit type in the
     * specified territory
     *
     * @return the UnitEntity of the unitType
     */
    public static UnitEntity getUnitForType(TerritoryEntity territory, UnitMappingEnum unitType){
        Optional<UnitEntity> unit = territory.getUnits().
                stream().
                filter(t -> t.getUnitType().equals(unitType)).findAny();
        if(!unit.isPresent()){
            throw new IllegalArgumentException("The territory " + territory.getName() + " does not have" +
                    unitType + " type of unit.");
        }
        return unit.get();
    }
}
