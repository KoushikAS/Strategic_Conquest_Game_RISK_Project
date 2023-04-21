package edu.duke.ece651.team13.server.service.order;

import edu.duke.ece651.team13.server.entity.*;
import edu.duke.ece651.team13.server.enums.UnitMappingEnum;
import edu.duke.ece651.team13.server.service.AttackerService;
import edu.duke.ece651.team13.server.service.PlayerService;
import edu.duke.ece651.team13.server.service.UnitService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static edu.duke.ece651.team13.server.MockDataUtil.getGameEntity;
import static edu.duke.ece651.team13.server.enums.OrderMappingEnum.ATTACK;
import static edu.duke.ece651.team13.server.enums.OrderMappingEnum.CARD_UNBREAKABLE_DEFENCE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CardUnbreakableDefenceServiceTest {

    private CardUnbreakableDefenseService service; //service under test

    @Mock
    private AttackerService attackerService;

    @BeforeEach
    void setUp() {
        service = new CardUnbreakableDefenseService(attackerService);
    }


    @Test
    void test_validateAndExecuteLocallyNotOnwedAttackingSame() throws IllegalArgumentException {
        GameEntity game = getGameEntity();
        PlayerEntity owner = new PlayerEntity();
        owner.setId(1L);
        PlayerEntity opponent = new PlayerEntity();
        opponent.setId(2L);
        TerritoryEntity source = game.getMap().getTerritories().get(0);
        source.setOwner(opponent);
        game.getPlayers().add(owner);

        OrderEntity order = new OrderEntity();
        order.setSource(source);
        order.setOrderType(CARD_UNBREAKABLE_DEFENCE);
        order.setPlayer(owner);

        assertThrows(IllegalArgumentException.class, () -> service.validateAndExecuteLocally(order, game));
    }


    @Test
    void test_executeOnGame() throws IllegalArgumentException {
        GameEntity game = getGameEntity();
        TerritoryEntity source = game.getMap().getTerritories().get(0);

        OrderEntity order = new OrderEntity();
        order.setSource(source);
        order.setOrderType(CARD_UNBREAKABLE_DEFENCE);

        service.executeOnGame(order, game);

        verify(attackerService, times(1)).clearAttackers(any(TerritoryEntity.class));
        verifyNoMoreInteractions(attackerService);
    }


}