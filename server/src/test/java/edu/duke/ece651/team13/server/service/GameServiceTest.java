package edu.duke.ece651.team13.server.service;

import edu.duke.ece651.team13.server.dto.GameDTO;
import edu.duke.ece651.team13.server.entity.*;
import edu.duke.ece651.team13.server.enums.GameStatusEnum;
import edu.duke.ece651.team13.server.enums.UnitMappingEnum;
import edu.duke.ece651.team13.server.repository.AttackerRepository;
import edu.duke.ece651.team13.server.repository.GameRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static edu.duke.ece651.team13.server.MockDataUtil.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class GameServiceTest {

    private GameService service; //service under test

    @Mock
    private GameRepository repository;

    @Mock
    private PlayerService playerService;

    @Mock
    private MapService mapService;

    @BeforeEach
    void setUp(){
        service = new GameServiceImpl(repository, playerService, mapService);
    }

    @Test
    void getGameTest(){
        GameEntity game = getGameEntity();

        when(repository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () ->service.getGame(1L));

        when(repository.findById(1L)).thenReturn(Optional.of(game));
        GameEntity actual = service.getGame(1L);
        assertEquals(game, actual);
    }

    @Test
    void getFreeGamesTest(){
        GameEntity game = getGameEntity();
        PlayerEntity player = getPlayerEntity();
        player.setUser(new UserEntity());
        game.getPlayers().add(player);

        List<GameEntity> games = new ArrayList<>();
        games.add(game);

        when(repository.findByRoundNo(0)).thenReturn(games);

        List<GameDTO> freeGames = service.getFreeGames();
        assertEquals(0, freeGames.size());

        PlayerEntity player1 = getPlayerEntity();
        player1.setUser(null);
        game.getPlayers().add(player1);

        List<GameDTO> freeGames1 = service.getFreeGames();
        assertEquals(1, freeGames1.size());
    }

    @Test
    void createGamesTest(){
        GameEntity game = getGameEntity();
        when(repository.save(any(GameEntity.class))).thenReturn(game);

        when(playerService.createPlayer(any(), any())).thenReturn(new PlayerEntity());

        GameEntity actual = service.createGame(1);
        assertEquals(game, actual);
    }

    @Test
    void updateGameRoundAndStatusTest(){
        GameEntity game = getGameEntity();
        when(repository.save(any(GameEntity.class))).thenReturn(game);
        GameEntity actual = service.updateGameRoundAndStatus(game, GameStatusEnum.PLAYING, 1);
        assertEquals(game, actual);
    }
}
