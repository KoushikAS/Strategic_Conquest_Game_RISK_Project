package edu.duke.ece651.team13.server.service;

import edu.duke.ece651.team13.server.entity.MapEntity;
import edu.duke.ece651.team13.server.entity.PlayerEntity;
import edu.duke.ece651.team13.server.entity.TerritoryConnectionEntity;
import edu.duke.ece651.team13.server.entity.TerritoryEntity;
import edu.duke.ece651.team13.server.repository.TerritoryConnectionRepository;
import edu.duke.ece651.team13.server.repository.TerritoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
@Slf4j
public class TerritoryServiceImpl implements TerritoryService {

    @Autowired
    private final TerritoryRepository repository;

    @Autowired
    private final TerritoryConnectionRepository territoryConnecitonRepository;

    @Override
    public TerritoryEntity createTerritory(String name, int unitNo, MapEntity map, PlayerEntity player) {
        TerritoryEntity territory = new TerritoryEntity();
        territory.setMap(map);
        map.getTerritories().add(territory);
        territory.setName(name);
        territory.setUnitNum(unitNo);
        territory.setOwner(player);
        return repository.save(territory);
    }


    @Override
    public TerritoryEntity getTerritoriesByMap(Long Id) {
        Optional<TerritoryEntity> territory = repository.findById(Id);
        if (territory.isPresent()) {
            return territory.get();
        } else {
            log.error("Did not find Territory with Map Id " + Id);
            throw new ResponseStatusException(NOT_FOUND, "Map with Id " + Id + " does not exists");
        }
    }

    @Override
    public TerritoryEntity updateTerritory(Long Id, PlayerEntity owner, int unit) {
        TerritoryEntity territory = getTerritoriesByMap(Id);
        territory.setOwner(owner);
        territory.setUnitNum(unit);
        return repository.save(territory);
    }

    @Override
    public void addNeighbour(TerritoryEntity territory1, TerritoryEntity territory2, Integer distance) {
        TerritoryConnectionEntity connection1 = territoryConnecitonRepository.save(new TerritoryConnectionEntity(territory2, territory1, distance));
        territory1.getConnections().add(connection1);
        TerritoryConnectionEntity connection2 = territoryConnecitonRepository.save(new TerritoryConnectionEntity(territory1, territory2, distance));
        territory2.getConnections().add(connection2);
    }


}
