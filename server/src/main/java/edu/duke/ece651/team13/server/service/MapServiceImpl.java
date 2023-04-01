package edu.duke.ece651.team13.server.service;

import edu.duke.ece651.team13.server.entity.GameEntity;
import edu.duke.ece651.team13.server.entity.MapEntity;
import edu.duke.ece651.team13.server.entity.PlayerEntity;
import edu.duke.ece651.team13.server.entity.TerritoryEntity;
import edu.duke.ece651.team13.server.repository.MapRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MapServiceImpl implements MapService {

    @Autowired
    private final MapRepository repository;

    @Autowired
    private final TerritoryService territoryService;

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
    public MapEntity createMap(int no_players, GameEntity gameEntity, List<PlayerEntity> players) {
        //TODO: Map getting initlaized accoording to no of players

        MapEntity mapEntity = repository.save(new MapEntity());
        mapEntity.setGame(gameEntity);
        gameEntity.setMap(mapEntity);

        switch (no_players) {
            case 2:
            case 3:
            case 4:
            default:
                createMapFor4players(mapEntity, players);
        }

        return mapEntity;
    }

    private void createMapFor4players(MapEntity mapEntity, List<PlayerEntity> players) {
        TerritoryEntity red = territoryService.createTerritory("Lab", 5, mapEntity, players.get(0));
        TerritoryEntity blue = territoryService.createTerritory("Boxer", 2, mapEntity, players.get(1));
        TerritoryEntity yellow = territoryService.createTerritory("Husky", 2, mapEntity, players.get(2));

        territoryService.addNeighbour(red, blue, 5);
        territoryService.addNeighbour(red, yellow, 5);

    }
}
