package edu.duke.ece651.team13.server.service;

import edu.duke.ece651.team13.server.entity.PlayerEntity;
import edu.duke.ece651.team13.server.entity.TerritoryEntity;
import edu.duke.ece651.team13.server.entity.TerritoryNeighbourEntity;
import edu.duke.ece651.team13.server.repository.TerritoryNeighbourMappingRepository;
import edu.duke.ece651.team13.server.repository.TerritoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TerritoryServiceImpl implements TerritoryService {

    @Autowired
    private final TerritoryRepository repository;

    @Autowired
    private final TerritoryNeighbourMappingRepository neighbourMappingRepository;

    @Override
    public TerritoryEntity createTerritory(String name, int unitNo){
        TerritoryEntity territory = new TerritoryEntity();
        territory.setName(name);
        territory.setUnitNum(unitNo);
        return repository.save(territory);
    }


    @Override
    public TerritoryEntity getTerritory(Long Id){
        Optional<TerritoryEntity> territory = repository.findById(Id);
        if (territory.isPresent()) {
            return territory.get();
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public TerritoryEntity updateTerritory(Long Id, PlayerEntity owner, int unit){
        TerritoryEntity territory = getTerritory(Id);
        territory.setOwner(owner);
        territory.setUnitNum(unit);
        return repository.save(territory);
    }

    @Override
    public void addNeighbour(Long Id1, Long Id2){
        TerritoryEntity territory1 = getTerritory(Id1);
        TerritoryEntity territory2 = getTerritory(Id2);

        neighbourMappingRepository.save(new TerritoryNeighbourEntity (territory2, territory1));
        neighbourMappingRepository.save(new TerritoryNeighbourEntity (territory1, territory2));
    }


}
