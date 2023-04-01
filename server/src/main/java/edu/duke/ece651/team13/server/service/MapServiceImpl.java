package edu.duke.ece651.team13.server.service;

import edu.duke.ece651.team13.server.entity.MapEntity;
import edu.duke.ece651.team13.server.entity.TerritoryEntity;
import edu.duke.ece651.team13.server.repository.MapRepository;
import edu.duke.ece651.team13.shared.map.V1Map;
import edu.duke.ece651.team13.shared.player.Player;
import edu.duke.ece651.team13.shared.territory.GameTerritory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MapServiceImpl implements MapService {

    @Autowired
    private final MapRepository repository;

    @Autowired
    private final TerritoryService territoryService;

    MapEntity getMapEntity(Long Id) {
        Optional<MapEntity> map = repository.findById(Id);
        if (map.isPresent()) {
            return map.get();
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public MapEntity getMap(Long mapId) {
        Optional<MapEntity> map = repository.findById(mapId);
        if (map.isPresent()) {
            return map.get();
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public MapEntity createMap(int no_players) {
        //TODO: Map getting initlaized accoording to no of players
        MapEntity mapEntity = repository.save(new MapEntity());

        TerritoryEntity red = territoryService.createTerritory("Red", 5, mapEntity);
        TerritoryEntity blue = territoryService.createTerritory("Blue", 2, mapEntity);
        TerritoryEntity yellow = territoryService.createTerritory("Yellow", 2, mapEntity);

        territoryService.addNeighbour(red, blue, 5);
        territoryService.addNeighbour(red, yellow, 5);

        return mapEntity;
    }
}
