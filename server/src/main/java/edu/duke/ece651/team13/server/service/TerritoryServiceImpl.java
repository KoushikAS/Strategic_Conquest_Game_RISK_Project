package edu.duke.ece651.team13.server.service;

import edu.duke.ece651.team13.server.entity.MapEntity;
import edu.duke.ece651.team13.server.entity.PlayerEntity;
import edu.duke.ece651.team13.server.entity.TerritoryEntity;
import edu.duke.ece651.team13.server.entity.TerritoryConnectionEntity;
import edu.duke.ece651.team13.server.repository.TerritoryConnectionRepository;
import edu.duke.ece651.team13.server.repository.TerritoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TerritoryServiceImpl implements TerritoryService {

    @Autowired
    private final TerritoryRepository repository;

    @Autowired
    private final TerritoryConnectionRepository territoryConnecitonRepository;

    @Override
    public TerritoryEntity createTerritory(String name, int unitNo, MapEntity map, PlayerEntity player){
        TerritoryEntity territory = new TerritoryEntity();
        territory.setMap(map);
        map.getTerritories().add(territory);
        territory.setName(name);
        territory.setUnitNum(unitNo);
        territory.setOwner(player);
        return repository.save(territory);
    }


    @Override
    public TerritoryEntity getTerritoriesByMap(Long Id){
        Optional<TerritoryEntity> territory = repository.findById(Id);
        if (territory.isPresent()) {
            return territory.get();
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public TerritoryEntity updateTerritory(Long Id, PlayerEntity owner, int unit){
        TerritoryEntity territory = getTerritoriesByMap(Id);
        territory.setOwner(owner);
        territory.setUnitNum(unit);
        return repository.save(territory);
    }

    @Override
    public void addNeighbour(TerritoryEntity territory1, TerritoryEntity territory2, Integer distance){
        TerritoryConnectionEntity connection1 = territoryConnecitonRepository.save(new TerritoryConnectionEntity(territory2, territory1, distance));
        territory1.getConnections().add(connection1);
        TerritoryConnectionEntity connection2 = territoryConnecitonRepository.save(new TerritoryConnectionEntity(territory1, territory2, distance));
        territory2.getConnections().add(connection2);
    }



}
