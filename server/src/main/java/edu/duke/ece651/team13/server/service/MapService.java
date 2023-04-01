package edu.duke.ece651.team13.server.service;

import edu.duke.ece651.team13.server.entity.MapEntity;
import edu.duke.ece651.team13.server.entity.PlayerEntity;
import edu.duke.ece651.team13.shared.enums.PlayerStatusEnum;
import edu.duke.ece651.team13.shared.map.V1Map;
import edu.duke.ece651.team13.shared.player.Player;

import java.util.HashMap;
import java.util.List;

public interface MapService {

    MapEntity getMap(Long mapId);

    MapEntity createMap(int no_players);
}
