package edu.duke.ece651.team13.server.service;

import edu.duke.ece651.team13.server.entity.TerritoryEntity;
import edu.duke.ece651.team13.server.entity.UnitEntity;
import edu.duke.ece651.team13.server.enums.UnitMappingEnum;

public interface UnitService {
    UnitEntity createUnit(UnitMappingEnum unitType, TerritoryEntity entity, int unitNum);

    UnitEntity updateUnit(UnitEntity unit, int unitNum);
}
