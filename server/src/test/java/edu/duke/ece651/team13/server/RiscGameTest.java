package edu.duke.ece651.team13.server;

import edu.duke.ece651.team13.shared.Player;
import edu.duke.ece651.team13.shared.territory.Territory;
import edu.duke.ece651.team13.shared.map.MapRO;
import edu.duke.ece651.team13.shared.order.MoveOrder;
import edu.duke.ece651.team13.shared.order.Order;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

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

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    void test_validateOrders() throws IOException {
        Mockito.when(mockedSocket.getInputStream()).thenReturn(mockedInputStream);
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
}