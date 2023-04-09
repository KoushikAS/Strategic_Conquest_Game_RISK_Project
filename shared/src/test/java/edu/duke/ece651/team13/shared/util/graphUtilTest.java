package edu.duke.ece651.team13.shared.util;

import edu.duke.ece651.team13.shared.MockDataUtil;
import edu.duke.ece651.team13.shared.map.MapRO;
import edu.duke.ece651.team13.shared.map.V1Map9Territories;
import edu.duke.ece651.team13.shared.player.HumanPlayer;
import edu.duke.ece651.team13.shared.player.PlayerRO;
import edu.duke.ece651.team13.shared.territory.TerritoryRO;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import static edu.duke.ece651.team13.shared.MockDataUtil.getUnconnectedTerritories;
import static edu.duke.ece651.team13.shared.util.graphUtil.DFS;
import static edu.duke.ece651.team13.shared.util.graphUtil.isConnectedGraph;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class graphUtilTest {

    @Test
    void test_isConnected() {
        MapRO m1 = new V1Map9Territories(5);

        Iterator<TerritoryRO> iterator = m1.getTerritoriesROIterator();
        ArrayList<TerritoryRO> list = new ArrayList<>();
        iterator.forEachRemaining(list::add);

        assertTrue(isConnectedGraph(list));
        assertFalse(isConnectedGraph(getUnconnectedTerritories(null)));

        //Checking if The constructor checks this condition.
        assertThrows(AssertionError.class, () -> new MockDataUtil.UnconnectedV1Map9Territories(5));
    }

    @Test
    void test_DFS() {
        PlayerRO owner = new HumanPlayer("Red");
        ArrayList<TerritoryRO> list = getUnconnectedTerritories(owner);
        HashSet<TerritoryRO> visited = new HashSet<>();

        DFS(list.get(1), visited, null);

        assertEquals(2, visited.size());
        assertFalse(visited.contains(list.get(0)));
        assertTrue(visited.contains(list.get(1)));
        assertTrue(visited.contains(list.get(2)));

        visited = new HashSet<>();

        DFS(list.get(1), visited, owner);

        assertEquals(1, visited.size());
        assertFalse(visited.contains(list.get(0)));
        assertTrue(visited.contains(list.get(1)));
        assertFalse(visited.contains(list.get(2)));
    }
}
