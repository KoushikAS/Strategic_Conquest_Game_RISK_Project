package edu.duke.ece651.team13.server.service;

import edu.duke.ece651.team13.server.dto.GameDTO;
import edu.duke.ece651.team13.server.entity.GameEntity;
import edu.duke.ece651.team13.server.entity.PlayerEntity;
import edu.duke.ece651.team13.server.enums.GameStatusEnum;
import edu.duke.ece651.team13.server.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
@Slf4j
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
            log.error("Did not find Game with Id " + gameId);
            throw new NoSuchElementException("Game with Id " + gameId + " does not exists");
        }
    }

    @Override
    public List<GameDTO> getFreeGames() {
        List<GameEntity> games = repository.findByRoundNo(0);
        return games.stream()
                .filter(game -> game.getPlayers().stream()
                        .anyMatch(player -> player.getUser() == null))
                .map(game -> new GameDTO(game.getId(), game.getPlayers().size()))
                .collect(Collectors.toList());
    }

    @Override
    public GameEntity createGame(int no_players) {
        GameEntity gameEntity = repository.save(new GameEntity());
        List<PlayerEntity> players = new ArrayList<>();
        for (int i = 0; i < no_players; i++) {
            players.add(playerService.createPlayer(playersName.get(i), gameEntity));
        }

        mapService.createMap( gameEntity, players);
        return gameEntity;
    }

    @Override
    public GameEntity updateGameRoundAndStatus(GameEntity game, GameStatusEnum status, int roundNo) {
        game.setStatus(status);
        game.setRoundNo(roundNo);
        return repository.save(game);
    }

}
