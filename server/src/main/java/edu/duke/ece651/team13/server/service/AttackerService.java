package edu.duke.ece651.team13.server.service;

import edu.duke.ece651.team13.server.entity.AttackerEntity;
import edu.duke.ece651.team13.server.entity.PlayerEntity;
import edu.duke.ece651.team13.server.entity.TerritoryEntity;
import edu.duke.ece651.team13.server.enums.UnitMappingEnum;

import java.util.List;

public interface AttackerService {

    List<AttackerEntity> getAttackers(TerritoryEntity territory);

    AttackerEntity addAttacker(TerritoryEntity territory, PlayerEntity player, UnitMappingEnum unitType, Integer UnitNo);

    void clearAttackers(TerritoryEntity territory);
}
