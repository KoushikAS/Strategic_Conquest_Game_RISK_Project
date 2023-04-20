package edu.duke.ece651.team13.server.service;

import edu.duke.ece651.team13.server.entity.PlayerEntity;
import edu.duke.ece651.team13.server.entity.TerritoryEntity;
import edu.duke.ece651.team13.server.entity.TerritoryViewEntity;
import edu.duke.ece651.team13.server.enums.TerritoryDisplayEnum;

public interface TerritoryViewService {
    TerritoryViewEntity createTerritoryView(TerritoryEntity territory, PlayerEntity viewer, PlayerEntity displayOwner, TerritoryDisplayEnum displayType);
}
