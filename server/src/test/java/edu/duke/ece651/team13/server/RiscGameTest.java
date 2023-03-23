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
import static edu.duke.ece651.team13.shared.enums.OrderMappingEnum.ATTACK;
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
    public void test_playOneTurn(){
        RiscGame game = getMockGame(2);

        Player red = game.getPlayerByName("Red");
        Player blue = game.getPlayerByName("Blue");

        //Move orders
        PlayerOrderInput move1 = new PlayerOrderInput(MOVE, "Rottweiler", "Dachshund", 0);
        PlayerOrderInput move2 = new PlayerOrderInput(MOVE, "Dachshund", "Rottweiler", 5);
        ArrayList<PlayerOrderInput> orders = new ArrayList<>();
        orders.add(move1);
        orders.add(move2);
        game.validateOrdersAndAddToList(orders, red);
        game.playOneTurn();

        Territory dachshund = game.getMapRO().getTerritoryByName("Dachshund");
        Territory rottweiler = game.getMapRO().getTerritoryByName("Rottweiler");

        // First round, don't get extra unit
        assertEquals(17, rottweiler.getUnitNum());
        assertEquals(7, dachshund.getUnitNum());
        assertEquals(red, rottweiler.getOwner());
        assertEquals(red, dachshund.getOwner());

        //Attack orders
        //Two players attack each other with ALL the units in one territory, automatically wins each other
        Territory bulldog = game.getMapRO().getTerritoryByName("Bulldog");
        Territory spaniel = game.getMapRO().getTerritoryByName("Spaniel");
        assertEquals(red, bulldog.getOwner());
        assertEquals(blue, spaniel.getOwner());

        PlayerOrderInput attack1 = new PlayerOrderInput(ATTACK, "Bulldog", "Spaniel", 12);
        PlayerOrderInput attack2 = new PlayerOrderInput(ATTACK, "Spaniel", "Bulldog", 12);
        orders.clear();
        orders.add(attack1);
        assertNull(game.validateOrdersAndAddToList(orders, red));
        orders.clear();
        orders.add(attack2);
        assertNull(game.validateOrdersAndAddToList(orders, blue));
        game.playOneTurn();

        assertEquals(blue, bulldog.getOwner());
        assertEquals(red, spaniel.getOwner());
        // Second round, get extra unit
        assertEquals(13, bulldog.getUnitNum());
        assertEquals(13, spaniel.getUnitNum());
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
        verifyUnitNumForAll(map, 12);
        game.addUnitToAllTerritory();
        verifyUnitNumForAll(map, 13);
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

    @Test
    public void test_addUnit_in_placementPhase(){
        V1Map map = getMap(2);
        RiscGame game = getMockGame(map, 2);
        verifyUnitNumForAll(map, 12);
        game.playOneTurn();
        verifyUnitNumForAll(map, 12);
        game.playOneTurn();
        verifyUnitNumForAll(map, 13);
    }

    private static void verifyUnitNumForAll(V1Map map, int expected) {
        for (Iterator<Territory> it = map.getTerritoriesIterator(); it.hasNext(); ) {
            Territory territory = it.next();
            assertEquals(expected, territory.getUnitNum());
        }
    }
}