package edu.duke.ece651.team13.server.service;

import edu.duke.ece651.team13.server.entity.GameEntity;
import edu.duke.ece651.team13.server.entity.MapEntity;
import edu.duke.ece651.team13.server.entity.PlayerEntity;

import java.util.List;

public interface MapService {

    MapEntity getMap(Long mapId);

    MapEntity createMap( GameEntity gameEntity, List<PlayerEntity> players);
}
