package edu.duke.ece651.team13.server;

import edu.duke.ece651.team13.server.entity.*;
import edu.duke.ece651.team13.server.enums.UnitMappingEnum;

import java.util.ArrayList;
import java.util.List;

public class MockDataUtil {
    private MockDataUtil() {
    }

    public static GameEntity getGameEntity() {
        GameEntity game = new GameEntity();
        game.setMap(getMapEntity());
        return game;
    }

    public static UserEntity getUserEntity() {
        return new UserEntity();
    }

    public static PlayerEntity getPlayerEntity() {
        return new PlayerEntity("Red");
    }

    public static MapEntity getMapEntity() {
        MapEntity map = new MapEntity();
        List<TerritoryEntity> territoryEntityList = new ArrayList<>();
        for (long i = 0; i < 5; i++) {
            TerritoryEntity territory = getTerritoryEntity();
            territory.setId(i);
            territoryEntityList.add(territory);
        }
        map.setTerritories(territoryEntityList);
        return map;
    }

    public static TerritoryEntity getTerritoryEntity() {
        TerritoryEntity territory = new TerritoryEntity();
        for (int i = 0; i < 10; i++) {
            territory.getUnits().add(getUnitEntity());
        }
        return territory;
    }

    public static UnitEntity getUnitEntity() {
        return new UnitEntity();
    }

    public static AttackerEntity getAttackerEntity(TerritoryEntity territory) {
        return new AttackerEntity(territory, getPlayerEntity(), UnitMappingEnum.LEVEL0, 5);
    }

}

