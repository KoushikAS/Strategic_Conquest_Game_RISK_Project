package edu.duke.ece651.team13.server;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.InputStream;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class RiscGameTest {


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

/**
    @Test
    void test_validateOrders() throws IOException {
        Mockito.when(mockedSocket.getInputStream()).thenReturn(mockedInputStream);
        RiscGame game = getMockGame(2);
        game.initPlayer("Green", mockedSocket);
        game.initPlayer("Blue", mockedSocket);
        game.initGame();

        MapRO map = game.getMap();

        Territory rottweiler = map.getTerritoryByName("Rottweiler");
        assertEquals("Green", rottweiler.getOwner().getName());
        rottweiler.setUnitNum(100);
        Territory dachshund = map.getTerritoryByName("Dachshund");
        assertEquals("Green", rottweiler.getOwner().getName());


        Territory boxer = map.getTerritoryByName("Boxer");
        assertEquals("Blue", boxer.getOwner().getName());
        boxer.setUnitNum(50);

        ArrayList<Order> orders = new ArrayList<>();
        Player green = game.getPlayerByName("Green");
        Player blue = game.getPlayerByName("Blue");
        orders.add(new MoveOrder(green, rottweiler, dachshund, 50));
        orders.add(new MoveOrder(green, dachshund, rottweiler, 40));
        assertNull(game.validateOrders(orders));
    }

    **/
}