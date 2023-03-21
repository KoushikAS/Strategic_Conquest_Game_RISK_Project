package edu.duke.ece651.team13.shared.util;

import edu.duke.ece651.team13.shared.MockDataUtil;
import edu.duke.ece651.team13.shared.map.MapRO;
import edu.duke.ece651.team13.shared.map.V1Map9Territories;
import edu.duke.ece651.team13.shared.territory.Territory;
import edu.duke.ece651.team13.shared.territory.TerritoryRO;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;

import static edu.duke.ece651.team13.shared.MockDataUtil.getUnconnectedTerritories;
import static edu.duke.ece651.team13.shared.util.graphUtil.isConnectedGraph;
import static org.junit.jupiter.api.Assertions.*;

public class graphUtilTest {

    @Test
    void test_isConnected() {
        MapRO m1 = new V1Map9Territories(5);

        Iterator<TerritoryRO> iterator = m1.getTerritoriesROIterator();
        ArrayList<TerritoryRO> list = new ArrayList<>();
        iterator.forEachRemaining(list::add);

        assertTrue(isConnectedGraph(list));
        assertFalse(isConnectedGraph(getUnconnectedTerritories()));

        //Checking if The constructor checks this condition.
        assertThrows(AssertionError.class, () -> new MockDataUtil.UnconnectedV1Map9Territories(5));
    }
}
