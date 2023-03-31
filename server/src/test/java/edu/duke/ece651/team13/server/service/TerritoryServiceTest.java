package edu.duke.ece651.team13.server.service;

import edu.duke.ece651.team13.server.entity.PlayerEntity;
import edu.duke.ece651.team13.server.entity.TerritoryEntity;
import edu.duke.ece651.team13.server.entity.TerritoryNeighbourEntity;
import edu.duke.ece651.team13.server.repository.PlayerRepository;
import edu.duke.ece651.team13.server.repository.TerritoryNeighbourMappingRepository;
import edu.duke.ece651.team13.server.repository.TerritoryRepository;
import edu.duke.ece651.team13.shared.enums.PlayerStatusEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;
import java.util.Optional;

import static edu.duke.ece651.team13.server.MockDataUtil.getPlayerEntity;
import static edu.duke.ece651.team13.server.MockDataUtil.getTerritoryEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class TerritoryServiceTest {

    private TerritoryService service; //service under test

    @Mock
    private TerritoryRepository repository;

    @Mock
    private TerritoryNeighbourMappingRepository neighbourMappingRepository;

    @BeforeEach
    void setUp(){
        service = new TerritoryServiceImpl(repository, neighbourMappingRepository);
    }

    @Test
    void getTerritoryTest(){
        TerritoryEntity territory = getTerritoryEntity();
        when(repository.findById(1L)).thenReturn(Optional.of(territory));
        when(repository.findById(2L)).thenReturn(Optional.empty());

        TerritoryEntity actual = service.getTerritory(1L);
        assertEquals(territory,actual);
        verify(repository, times(1)).findById(1L);
        verifyNoMoreInteractions(repository);

        assertThrows(NoSuchElementException.class, () -> service.getTerritory(2L));
    }

    @Test
    void createTerritoryTest(){
        TerritoryEntity territory = getTerritoryEntity();
        when(repository.save(any(TerritoryEntity.class))).thenReturn(territory);
        TerritoryEntity actual = service.createTerritory(territory.getName(), territory.getUnitNum());
        assertEquals(territory,actual);
        verify(repository, times(1)).save(any(TerritoryEntity.class));
        verifyNoMoreInteractions(repository);
    }

    @Test
    void updateTerritoryTest(){
        PlayerEntity player = getPlayerEntity();
        TerritoryEntity territory = getTerritoryEntity();
        when(repository.findById(1L)).thenReturn(Optional.of(territory));
        when(repository.save(any(TerritoryEntity.class))).thenReturn(territory);

        TerritoryEntity actual = service.updateTerritory(1L, player, 5);
        assertEquals(territory,actual);
        verify(repository, times(1)).findById(1L);
        verify(repository, times(1)).save(any(TerritoryEntity.class));
        verifyNoMoreInteractions(repository);
    }

    @Test
    void addNeighbourTest(){

        TerritoryEntity territory = getTerritoryEntity();
        when(repository.findById(1L)).thenReturn(Optional.of(territory));
        when(repository.findById(2L)).thenReturn(Optional.of(territory));
        when(neighbourMappingRepository.save(any(TerritoryNeighbourEntity.class))).thenReturn(new TerritoryNeighbourEntity());

        service.addNeighbour(1L, 2L);

        verify(repository, times(1)).findById(1L);
        verify(repository, times(1)).findById(2L);
        verify(neighbourMappingRepository, times(2)).save(any(TerritoryNeighbourEntity.class));
        verifyNoMoreInteractions(repository);
        verifyNoMoreInteractions(neighbourMappingRepository);
    }
}
