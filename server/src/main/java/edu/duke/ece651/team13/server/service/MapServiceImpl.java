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
    public MapEntity createMap( GameEntity gameEntity, List<PlayerEntity> players) {
        
        MapEntity mapEntity = new MapEntity();
        mapEntity.setGame(gameEntity);
        gameEntity.setMap(mapEntity);

        switch (players.size()) {
            case 2:
                createMapFor2players(mapEntity, players);
                break;
            case 3:
                createMapFor3players(mapEntity, players);
                break;
            default:
                createMapFor4players(mapEntity, players);
        }

        return repository.save(mapEntity);
    }

    private void createMapFor4players(MapEntity mapEntity, List<PlayerEntity> players) {
        TerritoryEntity rottweiler = territoryService.createTerritory("Rottweiler", 10, mapEntity, players.get(0), 100, 100);
        TerritoryEntity dachshund = territoryService.createTerritory("Dachshund", 10, mapEntity, players.get(0), 150, 150);
        TerritoryEntity beagle = territoryService.createTerritory("Beagle", 10, mapEntity, players.get(0), 150, 150);
        TerritoryEntity labrador = territoryService.createTerritory("Labrador", 10, mapEntity, players.get(0), 50, 50);
        TerritoryEntity poodle = territoryService.createTerritory("Poodle", 10, mapEntity, players.get(0), 100, 100);
        TerritoryEntity bulldog = territoryService.createTerritory("Bulldog", 10, mapEntity, players.get(0), 100, 100);

        TerritoryEntity boxer = territoryService.createTerritory("Boxer", 10, mapEntity, players.get(1), 50, 50);
        TerritoryEntity havanese = territoryService.createTerritory("Havanese", 10, mapEntity, players.get(1), 100, 100);
        TerritoryEntity spaniel = territoryService.createTerritory("Spaniel", 10, mapEntity, players.get(1), 100, 100);
        TerritoryEntity sheepdog = territoryService.createTerritory("Sheepdog", 10, mapEntity, players.get(1), 150, 150);
        TerritoryEntity akita = territoryService.createTerritory("Akita", 10, mapEntity, players.get(1), 100, 100);
        TerritoryEntity brittany = territoryService.createTerritory("Brittany", 10, mapEntity, players.get(1), 150, 150);

        TerritoryEntity vizsla = territoryService.createTerritory("Vizsla", 10, mapEntity, players.get(2), 100, 100);
        TerritoryEntity pug = territoryService.createTerritory("Pug", 10, mapEntity, players.get(2), 150, 150);
        TerritoryEntity chihuahua = territoryService.createTerritory("Chihuahua", 10, mapEntity, players.get(2), 100, 100);
        TerritoryEntity maltese = territoryService.createTerritory("Maltese", 10, mapEntity, players.get(2), 150, 150);
        TerritoryEntity mastiff = territoryService.createTerritory("Mastiff", 10, mapEntity, players.get(2), 100, 100);
        TerritoryEntity collie = territoryService.createTerritory("Collie", 10, mapEntity, players.get(2), 50, 50);

        TerritoryEntity dalmatian = territoryService.createTerritory("Dalmatian", 10, mapEntity, players.get(3), 50, 50);
        TerritoryEntity papillon = territoryService.createTerritory("Papillon", 10, mapEntity, players.get(3), 100, 100);
        TerritoryEntity setter = territoryService.createTerritory("Setter", 10, mapEntity, players.get(3), 100, 100);
        TerritoryEntity samoyed = territoryService.createTerritory("Samoyed", 10, mapEntity, players.get(3), 100, 100);
        TerritoryEntity bullmastiff = territoryService.createTerritory("Bullmastiff", 10, mapEntity, players.get(3), 150, 150);
        TerritoryEntity whippet = territoryService.createTerritory("Whippet", 10, mapEntity, players.get(3), 150, 150);

        territoryService.addNeighbour(dachshund, beagle, 3);
        territoryService.addNeighbour(dachshund, rottweiler, 5);
        territoryService.addNeighbour(dachshund, bulldog, 5);
        territoryService.addNeighbour(beagle, bulldog, 5);
        territoryService.addNeighbour(beagle, poodle, 5);
        territoryService.addNeighbour(rottweiler, bulldog, 3);
        territoryService.addNeighbour(bulldog, poodle, 3);
        territoryService.addNeighbour(rottweiler, labrador, 5);
        territoryService.addNeighbour(bulldog, labrador, 5);
        territoryService.addNeighbour(poodle, labrador, 5);

        territoryService.addNeighbour(brittany, sheepdog, 3);
        territoryService.addNeighbour(brittany, akita, 5);
        territoryService.addNeighbour(brittany, havanese, 5);
        territoryService.addNeighbour(sheepdog, havanese, 5);
        territoryService.addNeighbour(sheepdog, spaniel, 5);
        territoryService.addNeighbour(akita, havanese, 3);
        territoryService.addNeighbour(havanese, spaniel, 3);
        territoryService.addNeighbour(akita, boxer, 5);
        territoryService.addNeighbour(havanese, boxer, 5);
        territoryService.addNeighbour(spaniel, boxer, 5);

        territoryService.addNeighbour(pug, maltese, 3);
        territoryService.addNeighbour(pug, mastiff, 5);
        territoryService.addNeighbour(pug, vizsla, 5);
        territoryService.addNeighbour(maltese, vizsla, 5);
        territoryService.addNeighbour(maltese, chihuahua, 5);
        territoryService.addNeighbour(mastiff, vizsla, 3);
        territoryService.addNeighbour(vizsla, chihuahua, 3);
        territoryService.addNeighbour(mastiff, collie, 5);
        territoryService.addNeighbour(vizsla, collie, 5);
        territoryService.addNeighbour(chihuahua, collie, 5);

        territoryService.addNeighbour(bullmastiff, whippet, 3);
        territoryService.addNeighbour(bullmastiff, papillon, 5);
        territoryService.addNeighbour(bullmastiff, setter, 5);
        territoryService.addNeighbour(whippet, setter, 5);
        territoryService.addNeighbour(whippet, samoyed, 5);
        territoryService.addNeighbour(papillon, setter, 3);
        territoryService.addNeighbour(setter, samoyed, 3);
        territoryService.addNeighbour(papillon, dalmatian, 5);
        territoryService.addNeighbour(setter, dalmatian, 5);
        territoryService.addNeighbour(samoyed, dalmatian, 5);

        territoryService.addNeighbour(rottweiler, akita, 7);
        territoryService.addNeighbour(rottweiler, boxer, 7);
        territoryService.addNeighbour(poodle, mastiff, 7);
        territoryService.addNeighbour(labrador, mastiff, 7);

        territoryService.addNeighbour(spaniel, dalmatian, 7);
        territoryService.addNeighbour(spaniel, papillon, 7);

        territoryService.addNeighbour(samoyed, collie, 7);
        territoryService.addNeighbour(samoyed, chihuahua, 7);

        territoryService.addNeighbour(boxer, labrador, 7);
        territoryService.addNeighbour(labrador, collie, 7);
        territoryService.addNeighbour(collie, dalmatian, 7);
        territoryService.addNeighbour(dalmatian, boxer, 7);
        territoryService.addNeighbour(labrador, dalmatian, 7);
        territoryService.addNeighbour(boxer, collie, 7);
    }

    private void createMapFor3players(MapEntity mapEntity, List<PlayerEntity> players){
        TerritoryEntity rottweiler = territoryService.createTerritory("Rottweiler", 10, mapEntity, players.get(0), 100, 100);
        TerritoryEntity dachshund = territoryService.createTerritory("Dachshund", 10, mapEntity, players.get(0), 100, 100);
        TerritoryEntity beagle = territoryService.createTerritory("Beagle", 10, mapEntity, players.get(0), 100, 100);
        TerritoryEntity labrador = territoryService.createTerritory("Labrador", 10, mapEntity, players.get(0), 150, 150);
        TerritoryEntity poodle = territoryService.createTerritory("Poodle", 10, mapEntity, players.get(0), 50, 50);
        TerritoryEntity bulldog = territoryService.createTerritory("Bulldog", 10, mapEntity, players.get(0), 150, 150);

        TerritoryEntity boxer = territoryService.createTerritory("Boxer", 10, mapEntity, players.get(1), 150, 150);
        TerritoryEntity havanese = territoryService.createTerritory("Havanese", 10, mapEntity, players.get(1), 50, 50);
        TerritoryEntity spaniel = territoryService.createTerritory("Spaniel", 10, mapEntity, players.get(1), 100, 100);
        TerritoryEntity sheepdog = territoryService.createTerritory("Sheepdog", 10, mapEntity, players.get(1), 100, 100);
        TerritoryEntity akita = territoryService.createTerritory("Akita", 10, mapEntity, players.get(1), 150, 150);
        TerritoryEntity brittany = territoryService.createTerritory("Brittany", 10, mapEntity, players.get(1), 100, 100);

        TerritoryEntity vizsla = territoryService.createTerritory("Vizsla", 10, mapEntity, players.get(2), 50, 50);
        TerritoryEntity pug = territoryService.createTerritory("Pug", 10, mapEntity, players.get(2), 100, 100);
        TerritoryEntity chihuahua = territoryService.createTerritory("Chihuahua", 10, mapEntity, players.get(2), 100, 100);
        TerritoryEntity maltese = territoryService.createTerritory("Maltese", 10, mapEntity, players.get(2), 100, 100);
        TerritoryEntity mastiff = territoryService.createTerritory("Mastiff", 10, mapEntity, players.get(2), 150, 150);
        TerritoryEntity collie = territoryService.createTerritory("Collie", 10, mapEntity, players.get(2), 150, 150);

        territoryService.addNeighbour(labrador, bulldog, 3);
        territoryService.addNeighbour(labrador, rottweiler, 5);
        territoryService.addNeighbour(labrador, dachshund, 5);
        territoryService.addNeighbour(dachshund, bulldog, 5);
        territoryService.addNeighbour(bulldog, beagle, 5);
        territoryService.addNeighbour(rottweiler, dachshund, 3);
        territoryService.addNeighbour(dachshund, beagle, 3);
        territoryService.addNeighbour(rottweiler, poodle, 5);
        territoryService.addNeighbour(dachshund, poodle, 5);
        territoryService.addNeighbour(beagle, poodle, 5);
        territoryService.addNeighbour(spaniel, havanese, 5);
        territoryService.addNeighbour(brittany, havanese, 5);
        territoryService.addNeighbour(sheepdog, havanese, 5);
        territoryService.addNeighbour(spaniel, brittany, 3);
        territoryService.addNeighbour(brittany, sheepdog, 3);
        territoryService.addNeighbour(spaniel, boxer, 5);
        territoryService.addNeighbour(brittany, boxer, 5);
        territoryService.addNeighbour(brittany, akita, 5);
        territoryService.addNeighbour(sheepdog, akita, 5);
        territoryService.addNeighbour(boxer, akita, 3);
        territoryService.addNeighbour(vizsla, pug, 5);
        territoryService.addNeighbour(vizsla, chihuahua, 5);
        territoryService.addNeighbour(vizsla, maltese, 5);
        territoryService.addNeighbour(pug, chihuahua, 3);
        territoryService.addNeighbour(chihuahua, maltese, 3);
        territoryService.addNeighbour(mastiff, collie, 3);
        territoryService.addNeighbour(pug, mastiff, 5);
        territoryService.addNeighbour(chihuahua, mastiff, 5);
        territoryService.addNeighbour(chihuahua, collie, 5);
        territoryService.addNeighbour(maltese, collie, 5);

        territoryService.addNeighbour(rottweiler, spaniel, 7);
        territoryService.addNeighbour(rottweiler, havanese, 7);
        territoryService.addNeighbour(beagle, vizsla, 7);
        territoryService.addNeighbour(beagle, pug, 7);
        territoryService.addNeighbour(poodle, spaniel, 7);
        territoryService.addNeighbour(poodle, havanese, 7);
        territoryService.addNeighbour(poodle, vizsla, 7);
        territoryService.addNeighbour(poodle, pug, 7);
        territoryService.addNeighbour(havanese, vizsla, 7);
        territoryService.addNeighbour(havanese, maltese, 7);
        territoryService.addNeighbour(vizsla, sheepdog, 7);
        territoryService.addNeighbour(sheepdog, maltese, 7);
    }

    private void createMapFor2players(MapEntity mapEntity, List<PlayerEntity> players){
        TerritoryEntity rottweiler = territoryService.createTerritory ("Rottweiler", 10, mapEntity, players.get(0), 100, 100);
        TerritoryEntity dachshund = territoryService.createTerritory ("Dachshund", 10, mapEntity, players.get(0), 100, 100);
        TerritoryEntity beagle = territoryService.createTerritory ("Beagle", 10, mapEntity, players.get(0), 100, 100);
        TerritoryEntity labrador = territoryService.createTerritory ("Labrador", 10, mapEntity, players.get(0), 50, 50);
        TerritoryEntity poodle = territoryService.createTerritory ("Poodle", 10, mapEntity, players.get(0), 50, 50);
        TerritoryEntity bulldog = territoryService.createTerritory ("Bulldog", 10, mapEntity, players.get(0), 50, 50);

        TerritoryEntity boxer = territoryService.createTerritory ("Boxer", 10, mapEntity, players.get(1), 50, 50);
        TerritoryEntity havanese = territoryService.createTerritory ("Havanese", 10, mapEntity, players.get(1), 50, 50);
        TerritoryEntity spaniel = territoryService.createTerritory ("Spaniel", 10, mapEntity, players.get(1), 50, 50);
        TerritoryEntity sheepdog = territoryService.createTerritory ("Sheepdog", 10, mapEntity, players.get(1), 100, 100);
        TerritoryEntity akita = territoryService.createTerritory ("Akita", 10, mapEntity, players.get(1), 100, 100);
        TerritoryEntity brittany = territoryService.createTerritory ("Brittany", 10, mapEntity, players.get(1), 100, 100);

        territoryService.addNeighbour(rottweiler, dachshund, 3);
        territoryService.addNeighbour(dachshund, beagle, 3);
        territoryService.addNeighbour(rottweiler, labrador, 5);
        territoryService.addNeighbour(rottweiler, poodle, 5);
        territoryService.addNeighbour(dachshund, poodle, 5);
        territoryService.addNeighbour(dachshund, bulldog, 5);
        territoryService.addNeighbour(beagle, bulldog, 5);
        territoryService.addNeighbour(labrador, poodle, 3);
        territoryService.addNeighbour(poodle, bulldog, 3);

        territoryService.addNeighbour(boxer, havanese, 3);
        territoryService.addNeighbour(havanese, spaniel, 3);
        territoryService.addNeighbour(boxer, sheepdog, 5);
        territoryService.addNeighbour(sheepdog, havanese, 5);
        territoryService.addNeighbour(havanese, akita, 5);
        territoryService.addNeighbour(akita, spaniel, 5);
        territoryService.addNeighbour(spaniel, brittany, 5);
        territoryService.addNeighbour(sheepdog, akita, 3);
        territoryService.addNeighbour(akita, brittany, 3);

        territoryService.addNeighbour(labrador, boxer, 7);
        territoryService.addNeighbour(labrador, havanese, 7);
        territoryService.addNeighbour(poodle, havanese, 7);
        territoryService.addNeighbour(poodle, spaniel, 7);
        territoryService.addNeighbour(bulldog, spaniel, 7);
    }
}
