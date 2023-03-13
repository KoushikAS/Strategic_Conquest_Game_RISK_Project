package edu.duke.ece651.team13.shared.order;

import edu.duke.ece651.team13.shared.AttackerInfo;
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
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

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

        PlayerOrder order1 = new AttackOrder(
                null,
                new HumanPlayer("Green", mockedReader),
                map1.getTerritoryByName("Boxer"), map1.getTerritoryByName("Poodle"), 0);
        PlayerOrder order2 = order1.getOrderOnNewMap(map2);
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
        PlayerOrder order1 = new AttackOrder(
                null, green, boxer, poodle, 10);
        order1.act();
        Iterator<AttackerInfo> it = poodle.getAttackerIterator();
        assertEquals(10, it.next().getUnitNum());
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

        PlayerOrder order1 = new AttackOrder(
                null, green, boxer, poodle, 10);
        order1.actOnMap(map2);
        Iterator<AttackerInfo> it = poodle.getAttackerIterator();
        assertFalse(it.hasNext());
        assertEquals(10, boxer.getUnitNum());
        Iterator<AttackerInfo> cloneIt = clonePoodle.getAttackerIterator();
        assertEquals(10, cloneIt.next().getUnitNum());
        assertEquals(0, cloneBoxer.getUnitNum());

    }
}