package edu.duke.ece651.team13.server.service;

import edu.duke.ece651.team13.server.entity.*;
import edu.duke.ece651.team13.server.repository.TerritoryConnectionRepository;
import edu.duke.ece651.team13.server.repository.TerritoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class TerritoryServiceImpl implements TerritoryService {

    @Autowired
    private final TerritoryRepository repository;

    @Autowired
    private final TerritoryConnectionRepository territoryConnectionRepository;

    @Override
    public TerritoryEntity createTerritory(String name, MapEntity map, PlayerEntity player, int foodProduction, int techProduction) {
        TerritoryEntity territory = new TerritoryEntity();
        territory.setMap(map);
        map.getTerritories().add(territory);
        territory.setName(name);
        territory.setOwner(player);
        territory.setFoodProduction(foodProduction);
        territory.setTechProduction(techProduction);
        return repository.save(territory);
    }


    @Override
    public TerritoryEntity getTerritoriesById(Long Id) {
        Optional<TerritoryEntity> territory = repository.findById(Id);
        if (territory.isPresent()) {
            return territory.get();
        } else {
            log.error("Did not find Territory with Id " + Id);
            throw new NoSuchElementException("Territory with Id " + Id + " does not exists");
        }
    }

    @Override
    public List<TerritoryEntity> getTerritoriesByPlayer(PlayerEntity player) {
        return repository.findByOwner(player);
    }

    @Override
    public TerritoryEntity updateTerritoryOwner(TerritoryEntity territory, PlayerEntity owner) {
        territory.setOwner(owner);
        return repository.save(territory);
    }

    @Override
    public void addNeighbour(TerritoryEntity territory1, TerritoryEntity territory2, Integer distance) {
        TerritoryConnectionEntity connection1 = territoryConnectionRepository.save(new TerritoryConnectionEntity(territory2, territory1, distance));
        territory1.getConnections().add(connection1);
        TerritoryConnectionEntity connection2 = territoryConnectionRepository.save(new TerritoryConnectionEntity(territory1, territory2, distance));
        territory2.getConnections().add(connection2);
    }

    @Override
    public TerritoryEntity updateTerritoryUnits(TerritoryEntity territory, List<UnitEntity> units) {
        territory.setUnits(units);
        return repository.save(territory);
    }

}
