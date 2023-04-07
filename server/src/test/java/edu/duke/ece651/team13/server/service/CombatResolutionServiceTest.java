package edu.duke.ece651.team13.server.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;


@ExtendWith(MockitoExtension.class)
public class CombatResolutionServiceTest {

    private CombatResolutionService service; //service under test

    @Mock
    private AttackerService attackerService;

    @Mock
    private TerritoryService territoryService;

    @Mock
    private UnitService unitService;

    @BeforeEach
    void setUp(){
        service = new CombatResolutionServiceImpl(attackerService, territoryService, unitService);
    }


}
