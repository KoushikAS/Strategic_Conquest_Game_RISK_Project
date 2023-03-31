package edu.duke.ece651.team13.server.service;

import edu.duke.ece651.team13.server.entity.PlayerEntity;
import edu.duke.ece651.team13.server.entity.TerritoryEntity;
import edu.duke.ece651.team13.shared.enums.PlayerStatusEnum;
import edu.duke.ece651.team13.shared.player.PlayerRO;
import edu.duke.ece651.team13.shared.territory.TerritoryRO;

/**
 * The interface of Territory
 */
public interface TerritoryService  {

  TerritoryEntity createTerritory(String name, int unitNo);

  TerritoryEntity getTerritory(Long Id);

  TerritoryEntity updateTerritory(Long Id, PlayerEntity owner, int unit);

  void addNeighbour(Long Id1, Long Id2);
}
