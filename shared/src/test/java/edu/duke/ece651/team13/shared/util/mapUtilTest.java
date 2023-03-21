package edu.duke.ece651.team13.shared.util;

import edu.duke.ece651.team13.shared.map.MapRO;
import org.junit.jupiter.api.Test;

import static edu.duke.ece651.team13.shared.MockDataUtil.getInitalisedV1Map24MapRO;
import static edu.duke.ece651.team13.shared.util.mapUtil.isPlayerLost;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class mapUtilTest {

    @Test
    public void testisPlayerLost() {
        MapRO mapRO = getInitalisedV1Map24MapRO();

        assertTrue(isPlayerLost(mapRO, "RandomUnknownPlayer"));
        assertFalse(isPlayerLost(mapRO, "Red"));
    }
}
