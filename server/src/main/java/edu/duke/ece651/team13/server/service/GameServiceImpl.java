package edu.duke.ece651.team13.server.service;

import edu.duke.ece651.team13.server.Game;
import edu.duke.ece651.team13.server.entity.GameEntity;
import edu.duke.ece651.team13.server.entity.MapEntity;
import edu.duke.ece651.team13.server.entity.PlayerEntity;
import edu.duke.ece651.team13.server.entity.TerritoryEntity;
import edu.duke.ece651.team13.server.repository.GameRepository;
import edu.duke.ece651.team13.server.repository.MapRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {

    @Autowired
    private final GameRepository repository;

    @Autowired
    private final PlayerService playerService;

    @Autowired
    private final MapService mapService;

    private final List<String> playersName = Arrays.asList("Red", "Blue", "Green", "Yellow");

    @Override
    public GameEntity getGame(Long gameId) {
        Optional<GameEntity> game = repository.findById(gameId);
        if (game.isPresent()) {
            return game.get();
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public GameEntity createGame(int no_players) {
        //TODO: Map getting initlaized accoording to no of players
        GameEntity gameEntity = repository.save(new GameEntity());
        List<PlayerEntity> players = new ArrayList<>();
        for(int i =0; i < no_players; i++){
            players.add(playerService.createPlayer(playersName.get(i), gameEntity));
        }

        mapService.createMap(no_players, gameEntity, players);
        return gameEntity;
    }
}
