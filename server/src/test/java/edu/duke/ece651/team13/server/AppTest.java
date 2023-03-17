package edu.duke.ece651.team13.server;


import edu.duke.ece651.team13.shared.map.V1Map12Territories;
import edu.duke.ece651.team13.shared.map.V1Map18Territories;
import edu.duke.ece651.team13.shared.map.V1Map24Territories;
import edu.duke.ece651.team13.shared.player.Player;
import edu.duke.ece651.team13.shared.map.MapRO;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static edu.duke.ece651.team13.server.App.getMap;
import static edu.duke.ece651.team13.server.App.getPlayers;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class AppTest {
    @Test
    void test_GetMessage() {
        App a = new App();
    }

    @Test
    void test_getMap() {
        assertThrows(AssertionError.class, () -> getMap(5));

        MapRO map2player = getMap(2);
        assertEquals(map2player.getClass(), V1Map12Territories.class);

        MapRO map3player = getMap(3);
        assertEquals(map3player.getClass(), V1Map18Territories.class);

        MapRO map4player = getMap(4);
        assertEquals(map4player.getClass(), V1Map24Territories.class);

    }

    @Test
    void test_getPlayers() {
        assertThrows(AssertionError.class, () -> getPlayers(5));

        ArrayList<Player> player2 = getPlayers(2);
        assertEquals(2, player2.size());

        ArrayList<Player> player3 = getPlayers(3);
        assertEquals(3, player3.size());

        ArrayList<Player> player4 = getPlayers(4);
        assertEquals(4, player4.size());
    }
}

