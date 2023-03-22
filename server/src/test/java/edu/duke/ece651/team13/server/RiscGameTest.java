package edu.duke.ece651.team13.server;

import edu.duke.ece651.team13.shared.AttackerInfo;
import edu.duke.ece651.team13.shared.enums.PlayerStatusEnum;
import edu.duke.ece651.team13.shared.map.MapRO;
import edu.duke.ece651.team13.shared.map.V1Map;
import edu.duke.ece651.team13.shared.order.PlayerOrderInput;
import edu.duke.ece651.team13.shared.player.Player;
import edu.duke.ece651.team13.shared.territory.Territory;
import org.junit.jupiter.api.Test;

import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

import static edu.duke.ece651.team13.server.App.getMap;
import static edu.duke.ece651.team13.server.MockDataUtil.getMockGame;
import static edu.duke.ece651.team13.shared.enums.OrderMappingEnum.MOVE;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RiscGameTest {

    @Test
    void test_validateOrders() {
        RiscGame game = getMockGame(2);

        MapRO map = game.getMapRO();

        Territory rottweiler = map.getTerritoryByName("Rottweiler");
        assertEquals("Red", rottweiler.getOwner().getName());
        rottweiler.setUnitNum(100);
        assertEquals("Red", rottweiler.getOwner().getName());

        ArrayList<PlayerOrderInput> orders = new ArrayList<>();
        Player red = game.getPlayerByName("Red");

        orders.add(new PlayerOrderInput(MOVE, "Rottweiler", "Dachshund", 50));
        orders.add(new PlayerOrderInput(MOVE, "Dachshund", "Rottweiler", 40));
        assertNull(game.validateOrdersAndAddToList(orders, red));


        orders.add(new PlayerOrderInput(MOVE, "Rottweiler", "Dachshund", 100));
        assertEquals("Invalid move order: Don't have sufficient unit number in the territory.", game.validateOrdersAndAddToList(orders, red));
    }

    @Test
    void test_resolveCombatInOneTerritory() {

        RiscGame game = spy(getMockGame(4, new Dice(1, 20)));

        Player defender = game.getPlayerByName("Red");
        Player attacker1 = game.getPlayerByName("Blue");
        Player attacker2 = game.getPlayerByName("Green");
        Player attacker3 = game.getPlayerByName("Yellow");

        MapRO map = game.getMapRO();
        Territory rottweiler = map.getTerritoryByName("Rottweiler");
        assertEquals("Red", rottweiler.getOwner().getName());

        // Dice always return the same value, defender should win
        when(game.rollDice()).thenReturn(1, 2, 3, 4, 5, 5, 4, 5, 6, 5, 3, 3, 6, 5);
        ArrayList<AttackerInfo> attackerInfos = new ArrayList<>();
        attackerInfos.add(new AttackerInfo(defender, 3));
        attackerInfos.add(new AttackerInfo(attacker1, 1));
        attackerInfos.add(new AttackerInfo(attacker2, 2));
        attackerInfos.add(new AttackerInfo(attacker3, 2));
        when(game.getWarParties(rottweiler)).thenReturn(attackerInfos);

        rottweiler.setUnitNum(3);
        // 3, 1, 2, 2 -> 2, 1, 2, 2 -> 2, (0), 2, 2 -> 2,2,2 -> 2,1,2 -> 1,1,2 -> 1,(0),2 -> (0),2
//        Mockito.when(mockedDice.roll()).thenReturn(1,2,3,4,5,5,4,5,6,5,3,3,6,5);
        game.resolveCombatInOneTerritory(rottweiler);
        assertEquals("Yellow", rottweiler.getOwner().getName());
        assertEquals(2, rottweiler.getUnitNum());
        assertTrue(rottweiler.getAttackers().isEmpty());
    }

    @Test
    void test_initPlayer() {
        RiscGame game = getMockGame(2);

        assertNull(game.getPlayerByName("Red").getSocket());
        assertNull(game.getPlayerByName("Blue").getSocket());

        Socket s1 = mock(Socket.class);
        Socket s2 = mock(Socket.class);

        game.initPlayer("Red", s1);
        game.initPlayer("Blue", s2);

        assertEquals(s1, game.getPlayerByName("Red").getSocket());
        assertEquals(s2, game.getPlayerByName("Blue").getSocket());
    }

    @Test
    public void test_checkLostPlayer() {
        V1Map map = getMap(2);
        RiscGame game = getMockGame(map, 2);
        Player red = game.getPlayerByName("Red");
        Player blue = game.getPlayerByName("Blue");
        // pre-checking when red is playing
        game.checkLostPlayer();
        assertEquals(PlayerStatusEnum.PLAYING, red.getStatus());
        Iterator<Territory> it = map.getTerritoriesIterator();
        while (it.hasNext()) {
            Territory t = it.next();
            t.setOwner(blue);
        }
        // post-checking when red has lost
        game.checkLostPlayer();
        assertEquals(PlayerStatusEnum.LOSE, red.getStatus());
    }

    @Test
    public void test_addUnitToAllTerritory(){
        V1Map map = getMap(2);
        RiscGame game = getMockGame(map, 2);
        for (Iterator<Territory> it = map.getTerritoriesIterator(); it.hasNext(); ){
            Territory territory = it.next();
            assertEquals(12, territory.getUnitNum());
        }
        game.addUnitToAllTerritory();
        for (Iterator<Territory> it = map.getTerritoriesIterator(); it.hasNext(); ){
            Territory territory = it.next();
            assertEquals(13, territory.getUnitNum());
        }
    }

    @Test
    public void test_getPlayerByName() {
        RiscGame game = getMockGame(2);
        assertThrows(IllegalArgumentException.class, () -> game.getPlayerByName("Oliver"));
    }

    @Test
    public void test_getWinningPlayer() {
        RiscGame game = getMockGame(3);
        Player red = game.getPlayerByName("Red");
        Player blue = game.getPlayerByName("Blue");
        Player green = game.getPlayerByName("Green");
        game.checkLostPlayer();
        assertEquals(PlayerStatusEnum.PLAYING, red.getStatus());
        assertEquals(PlayerStatusEnum.PLAYING, blue.getStatus());
        assertEquals(PlayerStatusEnum.PLAYING, green.getStatus());
        // no winning player yet
        assertThrows(IllegalArgumentException.class, game::getWinningPlayer);
        game.fastForward();
        // red wins the game
        game.checkLostPlayer();
        assertEquals(PlayerStatusEnum.PLAYING, red.getStatus());
        assertEquals(PlayerStatusEnum.LOSE, blue.getStatus());
        assertEquals(PlayerStatusEnum.LOSE, green.getStatus());

        Player expected = game.getWinningPlayer();
        assertEquals(expected, red);
    }

    @Test
    public void test_fastForward() {
        RiscGame game = getMockGame(2);
        Player red = game.getPlayerByName("Red");
        Player blue = game.getPlayerByName("Blue");
        game.fastForward();
        assertEquals(PlayerStatusEnum.PLAYING, red.getStatus());
        assertEquals(PlayerStatusEnum.LOSE, blue.getStatus());
    }
}