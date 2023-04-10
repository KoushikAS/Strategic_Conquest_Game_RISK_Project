package edu.duke.ece651.team13.server.service;

import edu.duke.ece651.team13.server.entity.GameEntity;
import edu.duke.ece651.team13.server.entity.PlayerEntity;
import edu.duke.ece651.team13.server.enums.PlayerStatusEnum;
import edu.duke.ece651.team13.server.repository.PlayerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;
import java.util.Optional;

import static edu.duke.ece651.team13.server.MockDataUtil.getGameEntity;
import static edu.duke.ece651.team13.server.MockDataUtil.getPlayerEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class PlayerServiceTest {

    private PlayerService service; //service under test

    @Mock
    private PlayerRepository repository;

    @BeforeEach
    void setUp() {
        service = new PlayerServiceImpl(repository);
    }

    @Test
    void getPlayerTest() {
        PlayerEntity player = getPlayerEntity();
        when(repository.findById(1L)).thenReturn(Optional.of(player));
        when(repository.findById(2L)).thenReturn(Optional.empty());

        PlayerEntity actual = service.getPlayer(1L);
        assertEquals(player, actual);
        verify(repository, times(1)).findById(1L);
        verifyNoMoreInteractions(repository);

        assertThrows(NoSuchElementException.class, () -> service.getPlayer(2L));
    }

    @Test
    void createPlayerTest() {
        GameEntity game = getGameEntity();
        PlayerEntity player = getPlayerEntity();
        when(repository.save(any(PlayerEntity.class))).thenReturn(player);
        PlayerEntity actual = service.createPlayer(player.getName(), game);
        assertEquals(player, actual);
        verify(repository, times(1)).save(any(PlayerEntity.class));
        verifyNoMoreInteractions(repository);
    }

    @Test
    void updatePlayerStatusTest() {
        PlayerEntity player = getPlayerEntity();

        when(repository.save(any(PlayerEntity.class))).thenReturn(player);

        PlayerEntity actual = service.updatePlayerStatus(player, PlayerStatusEnum.LOSE);
        assertEquals(player, actual);

        verify(repository, times(1)).save(any(PlayerEntity.class));
        verifyNoMoreInteractions(repository);
    }
}
