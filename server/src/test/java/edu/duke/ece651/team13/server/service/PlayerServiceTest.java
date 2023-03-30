package edu.duke.ece651.team13.server.service;

import edu.duke.ece651.team13.server.entity.PlayerEntity;
import edu.duke.ece651.team13.server.repository.PlayerRepository;

import edu.duke.ece651.team13.shared.enums.PlayerStatusEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.NoSuchElementException;
import java.util.Optional;

import static edu.duke.ece651.team13.server.MockDataUtil.getPlayerEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class PlayerServiceTest {

    private PlayerService service; //service under test

    @Mock
    private PlayerRepository repository;

    @BeforeEach
    void setUp(){
        service = new PlayerServiceImpl(repository);
    }

    @Test
    void getPlayerTest(){
        PlayerEntity player = getPlayerEntity();
        when(repository.findById(1L)).thenReturn(Optional.of(player));
        when(repository.findById(2L)).thenReturn(Optional.empty());

        PlayerEntity actual = service.getPlayer(1L);
        assertEquals(player,actual);
        verify(repository, times(1)).findById(1L);
        verifyNoMoreInteractions(repository);

        assertThrows(NoSuchElementException.class, () -> service.getPlayer(2L));
    }

    @Test
    void createPlayerTest(){
        PlayerEntity player = getPlayerEntity();
        when(repository.save(any(PlayerEntity.class))).thenReturn(player);
        PlayerEntity actual = service.createPlayer(player.getName());
        assertEquals(player,actual);
        verify(repository, times(1)).save(any(PlayerEntity.class));
        verifyNoMoreInteractions(repository);
    }

    @Test
    void updatePlayerStatusTest(){
        PlayerEntity player = getPlayerEntity();
        when(repository.findById(1L)).thenReturn(Optional.of(player));
        when(repository.save(any(PlayerEntity.class))).thenReturn(player);

        PlayerEntity actual = service.updatePlayerStatus(1L, PlayerStatusEnum.LOSE);
        assertEquals(player,actual);
        verify(repository, times(1)).findById(1L);
        verify(repository, times(1)).save(any(PlayerEntity.class));
        verifyNoMoreInteractions(repository);
    }
}
