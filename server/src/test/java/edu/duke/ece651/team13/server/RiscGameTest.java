package edu.duke.ece651.team13.server;

import edu.duke.ece651.team13.shared.AttackerInfo;
import edu.duke.ece651.team13.shared.Player;
import edu.duke.ece651.team13.shared.map.MapRO;
import edu.duke.ece651.team13.shared.order.MoveOrder;
import edu.duke.ece651.team13.shared.order.Order;
import edu.duke.ece651.team13.shared.territory.Territory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

class RiscGameTest {
    @Test
    void test_getMaxPlayers() {
        RiscGame game = new RiscGame(2);
        assertEquals(2, game.getMaxPlayers());
    }

    @Test
    void test_getBoard() {
        RiscGame game = new RiscGame(2);
        Board expectedBoard = new RiscGameBoard(2);
        assertEquals(expectedBoard.getMap(), game.getBoard().getMap());
    }

    @Mock
    private Socket mockedSocket;

    @Mock
    private InputStream mockedInputStream;

    AutoCloseable openMocks;

    @BeforeEach
    void setUp(){
        openMocks = MockitoAnnotations.openMocks(this);
    }

    @Test
    void test_validateOrders() throws IOException {
        when(mockedSocket.getInputStream()).thenReturn(mockedInputStream);
        RiscGame game = new RiscGame(2);
        game.initPlayer("Green", mockedSocket);
        game.initPlayer("Blue", mockedSocket);
        game.initGame();

        MapRO map = game.getBoard().getMap();

        Territory rottweiler = map.getTerritoryByName("Rottweiler");
        assertEquals("Green", rottweiler.getOwner().getName());
        rottweiler.setUnitNum(100);
        Territory dachshund = map.getTerritoryByName("Dachshund");
        assertEquals("Green", rottweiler.getOwner().getName());


        Territory boxer = map.getTerritoryByName("Boxer");
        assertEquals("Blue", boxer.getOwner().getName());
        boxer.setUnitNum(50);
        Territory havanese = map.getTerritoryByName("Havanese");
        assertEquals("Blue", havanese.getOwner().getName());

        ArrayList<Order> orders = new ArrayList<>();
        Player green = game.getPlayerByName("Green");
        Player blue = game.getPlayerByName("Blue");
        orders.add(new MoveOrder(green, rottweiler, dachshund, 50));
        orders.add(new MoveOrder(green, dachshund, rottweiler, 40));
        assertNull(game.validateOrders(orders));

        orders.add(new MoveOrder(green, rottweiler, dachshund, 100));
        assertEquals("Invalid move order: Don't have sufficient unit number in the territory.", game.validateOrders(orders));
        orders.clear();

        orders.add(new MoveOrder(green, rottweiler, dachshund, 50));
        orders.add(new MoveOrder(blue, boxer, havanese, 30));
        assertNull(game.validateOrders(orders));
    }

    @Test
    void test_resolveCombatInOneTerritory() throws IOException {
        when(mockedSocket.getInputStream()).thenReturn(mockedInputStream);
        RiscGame game = spy(new RiscGame(4, new Dice(1,20)));
        when(game.getMaxPlayers()).thenReturn(4);
        game.initPlayer("Defender", mockedSocket);
        game.initPlayer("Attacker1", mockedSocket);
        game.initPlayer("Attacker2", mockedSocket);
        game.initPlayer("Attacker3", mockedSocket);
        game.initGame();

        Player defender = game.getPlayerByName("Defender");
        Player attacker1 = game.getPlayerByName("Attacker1");
        Player attacker2 = game.getPlayerByName("Attacker2");
        Player attacker3 = game.getPlayerByName("Attacker3");

        MapRO map = game.getBoard().getMap();
        Territory rottweiler = map.getTerritoryByName("Rottweiler");
        assertEquals("Defender", rottweiler.getOwner().getName());

        // Dice always return the same value, defender should win
        when(game.rollDice()).thenReturn(1,2,3,4,5,5,4,5,6,5,3,3,6,5);
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
        assertEquals("Attacker3", rottweiler.getOwner().getName());
        assertEquals(2, rottweiler.getUnitNum());
        assertTrue(rottweiler.getAttackers().isEmpty());
    }
}