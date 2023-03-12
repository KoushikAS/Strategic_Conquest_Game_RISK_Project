package edu.duke.ece651.team13.server;

import edu.duke.ece651.team13.shared.map.MapRO;
import org.junit.jupiter.api.Test;

import static edu.duke.ece651.team13.server.App.getMap;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AppTest {
    @Test
    void test_GetMessage() {
        App a = new App();
    }

    @Test
    void test_getMap() {
        MapRO map2player = getMap(2);
        assertEquals(12, map2player.getInitialUnit());

        MapRO map3player = getMap(3);
        assertEquals(18, map3player.getInitialUnit());

        MapRO map4player = getMap(4);
        assertEquals(24, map4player.getInitialUnit());
    }
}

