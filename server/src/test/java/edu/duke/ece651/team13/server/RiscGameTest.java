package edu.duke.ece651.team13.server;

import edu.duke.ece651.team13.shared.Player;
import edu.duke.ece651.team13.shared.map.MapRO;
import edu.duke.ece651.team13.shared.order.MoveOrder;
import edu.duke.ece651.team13.shared.order.Order;
import edu.duke.ece651.team13.shared.territory.Territory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.ArrayList;

import static edu.duke.ece651.team13.server.MockDataUtil.getMockGame;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class RiscGameTest {

    @Test
    void test_validateOrders() throws IOException {

        RiscGame game = getMockGame(2);

        MapRO map = game.getMap();

        Territory rottweiler = map.getTerritoryByName("Rottweiler");
        assertEquals("Red", rottweiler.getOwner().getName());
        rottweiler.setUnitNum(100);
        Territory dachshund = map.getTerritoryByName("Dachshund");
        assertEquals("Red", rottweiler.getOwner().getName());


        Territory boxer = map.getTerritoryByName("Boxer");
        assertEquals("Blue", boxer.getOwner().getName());
        boxer.setUnitNum(50);

        ArrayList<Order> orders = new ArrayList<>();
        Player green = game.getPlayerByName("Red");
        Player blue = game.getPlayerByName("Blue");
        orders.add(new MoveOrder(green, rottweiler, dachshund, 50));
        orders.add(new MoveOrder(green, dachshund, rottweiler, 40));
        assertNull(game.validateOrders(orders));
    }

}