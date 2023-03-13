package edu.duke.ece651.team13.shared.order;

import edu.duke.ece651.team13.shared.HumanPlayer;
import edu.duke.ece651.team13.shared.Player;
import edu.duke.ece651.team13.shared.map.MapRO;
import edu.duke.ece651.team13.shared.map.V1Map12Territories;
import edu.duke.ece651.team13.shared.territory.Territory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.BufferedReader;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AttackOrderTest {

    @Mock
    private BufferedReader mockedReader;

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
    void test_getOrderOnNewMap() {
        MapRO map1 = new V1Map12Territories(10);
        MapRO map2 = new V1Map12Territories(10);

        Order order1 = new AttackOrder(
                null,
                new HumanPlayer("Green", mockedReader),
                map1.getTerritoryByName("Boxer"), map1.getTerritoryByName("Poodle"), 0);
        Order order2 = order1.getOrderOnNewMap(map2);
        assertEquals("Boxer", order2.getSource().getName());
        assertEquals("Poodle", order2.getDestination().getName());

        order2.getSource().setUnitNum(100);
        order2.getDestination().setUnitNum(200);
        assertEquals(0, order1.getSource().getUnitNum());
        assertEquals(0, order1.getDestination().getUnitNum());
    }

    @Test
    void test_act() {
        MapRO map1 = new V1Map12Territories(10);
        Territory boxer = map1.getTerritoryByName("Boxer");
        Territory poodle = map1.getTerritoryByName("Poodle");
        Player green = new HumanPlayer("Green", mockedReader);
        boxer.setUnitNum(10);
        Order order1 = new AttackOrder(
                null, green, boxer, poodle, 10);
        order1.act();
        HashMap<Player, Integer> attackers = poodle.getAttackers();
        assertEquals(10, attackers.get(green));
        assertEquals(0, boxer.getUnitNum());
    }

    @Test
    void test_actOnMap(){
        MapRO map1 = new V1Map12Territories(10);

        Territory boxer = map1.getTerritoryByName("Boxer");
        Territory poodle = map1.getTerritoryByName("Poodle");
        Player green = new HumanPlayer("Green", mockedReader);

        boxer.setUnitNum(10);
        MapRO map2 = map1.replicate();
        Territory cloneBoxer = map2.getTerritoryByName("Boxer");
        Territory clonePoodle = map2.getTerritoryByName("Poodle");

        Order order1 = new AttackOrder(
                null, green, boxer, poodle, 10);
        order1.actOnMap(map2);
        HashMap<Player, Integer> attackers = poodle.getAttackers();
        assertTrue(attackers.isEmpty());
        assertEquals(10, boxer.getUnitNum());
        HashMap<Player, Integer> cloneAttackers = clonePoodle.getAttackers();
        assertEquals(10, cloneAttackers.get(green));
        assertEquals(0, cloneBoxer.getUnitNum());

    }
}