package edu.duke.ece651.team13.server.service;

import edu.duke.ece651.team13.server.entity.TerritoryEntity;
import edu.duke.ece651.team13.server.entity.TerritoryViewEntity;
import edu.duke.ece651.team13.server.entity.UnitEntity;
import edu.duke.ece651.team13.server.entity.UnitViewEntity;
import edu.duke.ece651.team13.server.enums.UnitMappingEnum;

public interface UnitViewService {
    UnitViewEntity createUnitView(TerritoryViewEntity territoryView, UnitMappingEnum unitType, int unitNum);
}
