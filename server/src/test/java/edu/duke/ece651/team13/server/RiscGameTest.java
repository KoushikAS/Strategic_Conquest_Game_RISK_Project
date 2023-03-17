package edu.duke.ece651.team13.server;

import edu.duke.ece651.team13.shared.AttackerInfo;
import edu.duke.ece651.team13.shared.Player;
import edu.duke.ece651.team13.shared.map.MapRO;
import edu.duke.ece651.team13.shared.order.MoveOrder;
import edu.duke.ece651.team13.shared.order.Order;
import edu.duke.ece651.team13.shared.territory.Territory;
import org.junit.jupiter.api.Test;

import java.net.Socket;
import java.util.ArrayList;

import static edu.duke.ece651.team13.server.MockDataUtil.getMockGame;
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
        Territory dachshund = map.getTerritoryByName("Dachshund");
        assertEquals("Red", rottweiler.getOwner().getName());


        Territory boxer = map.getTerritoryByName("Boxer");
        assertEquals("Blue", boxer.getOwner().getName());
        boxer.setUnitNum(50);
        Territory havanese = map.getTerritoryByName("Havanese");
        assertEquals("Blue", havanese.getOwner().getName());

        ArrayList<Order> orders = new ArrayList<>();
        Player green = game.getPlayerByName("Red");
        Player blue = game.getPlayerByName("Blue");
        orders.add(new MoveOrder(green, rottweiler, dachshund, 50));
        orders.add(new MoveOrder(green, dachshund, rottweiler, 40));
        assertNull(game.validateOrdersAndAddToList(orders));

        orders.add(new MoveOrder(green, rottweiler, dachshund, 100));
        assertEquals("Invalid move order: Don't have sufficient unit number in the territory.", game.validateOrdersAndAddToList(orders));
        orders.clear();

        orders.add(new MoveOrder(green, rottweiler, dachshund, 50));
        orders.add(new MoveOrder(blue, boxer, havanese, 30));
        assertNull(game.validateOrdersAndAddToList(orders));
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

}