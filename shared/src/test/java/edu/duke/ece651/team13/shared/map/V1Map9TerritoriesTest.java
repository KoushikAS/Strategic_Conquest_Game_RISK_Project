package edu.duke.ece651.team13.shared.map;

import edu.duke.ece651.team13.shared.territory.GameTerritory;
import edu.duke.ece651.team13.shared.territory.Territory;
import edu.duke.ece651.team13.shared.territory.TerritoryRO;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class V1Map9TerritoriesTest {
    @Test
    void test_constructor_illegal() {
        assertThrows(IllegalArgumentException.class, () -> new V1Map9Territories(0));
        assertThrows(IllegalArgumentException.class, () -> new V1Map9Territories(-1));
    }


    @Test
    void test_equals() {
        // TODO: Refactor this after refactoring V1Map9Territories.equals()
        MapRO m1 = new V1Map9Territories(1);
        MapRO m2 = new V1Map9Territories(2);
        assertNotEquals(m1, m2);
        assertNotEquals(m1, "map");
    }

    @Test
    void test_initMap() {
        MapRO m1 = new V1Map9Territories(5);

        Iterator<Territory> it1 = m1.getTerritoriesIterator();

        Territory narnia = it1.next();
        Territory midkemia = it1.next();
        Territory oz = it1.next();
        Territory gondor = it1.next();
        Territory elantris = it1.next();
        Territory scadrial = it1.next();
        Territory roshar = it1.next();
        Territory hogwarts = it1.next();
        Territory mordor = it1.next();

        assertEquals(narnia.getName(), "Narnia");
        Iterator<TerritoryRO> it = narnia.getNeighbourIterartor();
        assertEquals(midkemia, it.next());
        assertEquals(elantris, it.next());

        assertEquals(midkemia.getName(), "Midkemia");
        it = midkemia.getNeighbourIterartor();
        assertEquals(narnia, it.next());
        assertEquals(oz, it.next());
        assertEquals(scadrial, it.next());

        assertEquals(oz.getName(), "Oz");
        it = oz.getNeighbourIterartor();
        assertEquals(midkemia, it.next());
        assertEquals(gondor, it.next());
        assertEquals(mordor, it.next());

        assertEquals(gondor.getName(), "Gondor");
        it = gondor.getNeighbourIterartor();
        assertEquals(oz, it.next());
        assertEquals(mordor, it.next());

        assertEquals(elantris.getName(), "Elantris");
        it = elantris.getNeighbourIterartor();
        assertEquals(narnia, it.next());
        assertEquals(scadrial, it.next());
        assertEquals(roshar, it.next());

        assertEquals(scadrial.getName(), "Scadrial");
        it = scadrial.getNeighbourIterartor();
        assertEquals(midkemia, it.next());
        assertEquals(elantris, it.next());
        assertEquals(roshar, it.next());
        assertEquals(mordor, it.next());
        assertEquals(hogwarts, it.next());

        assertEquals(roshar.getName(), "Roshar");
        it = roshar.getNeighbourIterartor();
        assertEquals(elantris, it.next());
        assertEquals(scadrial, it.next());
        assertEquals(hogwarts, it.next());

        assertEquals(hogwarts.getName(), "Hogwarts");
        it = hogwarts.getNeighbourIterartor();
        assertEquals(scadrial, it.next());
        assertEquals(roshar, it.next());
        assertEquals(mordor, it.next());

        assertEquals(mordor.getName(), "Mordor");
        it = mordor.getNeighbourIterartor();
        assertEquals(oz, it.next());
        assertEquals(gondor, it.next());
        assertEquals(scadrial, it.next());
        assertEquals(hogwarts, it.next());

    }

    @Test
    void test_isConnected() {
        // Connected
        MapRO m1 = new V1Map9Territories(5);
        assertTrue(m1.isConnected());

        // Not connected
        assertThrows(AssertionError.class, () -> new UnconnectedV1Map9Territories(5));
    }

    @Test
    void test_replicate() {
        MapRO map = new V1Map9Territories(10);
        Iterator<Territory> it = map.getTerritoriesIterator();
        String firstName = it.next().getName();
        Territory firstT = map.getTerritoryByName(firstName);
        firstT.setUnitNum(100);
        String secondName = it.next().getName();
        Territory secondT = map.getTerritoryByName(secondName);
        secondT.setUnitNum(200);

        MapRO cloneMap = map.replicate();
        Territory cloneFirstT = cloneMap.getTerritoryByName(firstName);
        assertEquals(100, cloneFirstT.getUnitNum());
        Territory cloneSecondT = cloneMap.getTerritoryByName(secondName);
        assertEquals(200, cloneSecondT.getUnitNum());

        String thirdName = it.next().getName();
        Territory thirdT = map.getTerritoryByName(thirdName);
        thirdT.setUnitNum(300);
        Territory cloneThirdT = cloneMap.getTerritoryByName(thirdName);
        assertEquals(10, cloneThirdT.getUnitNum());

        firstT.setUnitNum(0);
        assertEquals(100, cloneFirstT.getUnitNum());

        for (Iterator<TerritoryRO> iter = firstT.getNeighbourIterartor(); iter.hasNext(); ) {
            TerritoryRO neighbor = iter.next();
            Territory cloneNeighbor = cloneMap.getTerritoryByName(neighbor.getName());
            assertTrue(isNeighborTo(cloneFirstT, cloneNeighbor));
        }
    }

    private boolean isNeighborTo(Territory t1, Territory t2) {
        for (Iterator<TerritoryRO> iter = t1.getNeighbourIterartor(); iter.hasNext(); ) {
            TerritoryRO neighbor = iter.next();
            if (neighbor == t2) return true;
        }
        return false;
    }

    @Test
    public void test_getInitialGroups() {
        // Creating Terrritores
        Territory narnia = new GameTerritory("Narnia");
        Territory midkemia = new GameTerritory("Midkemia");
        Territory oz = new GameTerritory("Oz");
        Territory gondor = new GameTerritory("Gondor");
        Territory elantris = new GameTerritory("Elantris");
        Territory scadrial = new GameTerritory("Scadrial");
        Territory roshar = new GameTerritory("Roshar");
        Territory hogwarts = new GameTerritory("Hogwarts");
        Territory mordor = new GameTerritory("Mordor");

        // create the expected result
        ArrayList<Iterator<Territory>> expected = new ArrayList<>();
        ArrayList<Territory> group1 = new ArrayList<>();
        group1.add(narnia);
        group1.add(midkemia);
        group1.add(oz);
        expected.add(group1.iterator());

        ArrayList<Territory> group2 = new ArrayList<>();
        group2.add(gondor);
        group2.add(mordor);
        group2.add(hogwarts);
        expected.add(group2.iterator());

        ArrayList<Territory> group3 = new ArrayList<>();
        group3.add(elantris);
        group3.add(scadrial);
        group3.add(roshar);
        expected.add(group3.iterator());

        MapRO map = new V1Map9Territories(10);
        ArrayList<Iterator<Territory>> actual = map.getInitialGroups();
        for (int i = 0; i < expected.size(); i++) {
            compareTerritoryIterators(expected.get(i), actual.get(i));
        }
    }

    /**
     * This helper method compares two territory iterators with the territory names
     *
     * @param it1 the first territory iterator
     * @param it2 the second territory iterator
     */
    private void compareTerritoryIterators(Iterator<Territory> it1, Iterator<Territory> it2) {
        while (it1.hasNext() && it2.hasNext()) {
            assertEquals(it1.next().getName(), it2.next().getName());
        }
        assertFalse(it1.hasNext());
        assertFalse(it2.hasNext());
    }

    /**
     * Helper class that initializes an unconnected map for testing
     */
    static class UnconnectedV1Map9Territories extends V1Map9Territories {
        public UnconnectedV1Map9Territories(int initialUnit) {
            super(initialUnit);
        }

        @Override
        protected void initMap(int initialUnit) {
            // Creating Terrritores
            Territory narnia = new GameTerritory("Narnia");
            Territory midkemia = new GameTerritory("Midkemia");
            Territory oz = new GameTerritory("Oz");
            Territory gondor = new GameTerritory("Gondor");
            Territory elantris = new GameTerritory("Elantris");
            Territory scadrial = new GameTerritory("Scadrial");
            Territory roshar = new GameTerritory("Roshar");
            Territory hogwarts = new GameTerritory("Hogwarts");
            Territory mordor = new GameTerritory("Mordor");

            // Narnia has no neighbors
            addTerritoriesNeighbours(midkemia, oz);
            addTerritoriesNeighbours(midkemia, scadrial);
            addTerritoriesNeighbours(oz, gondor);
            addTerritoriesNeighbours(oz, mordor);
            addTerritoriesNeighbours(gondor, mordor);
            addTerritoriesNeighbours(elantris, scadrial);
            addTerritoriesNeighbours(elantris, roshar);
            addTerritoriesNeighbours(scadrial, roshar);
            addTerritoriesNeighbours(scadrial, mordor);
            addTerritoriesNeighbours(scadrial, hogwarts);
            addTerritoriesNeighbours(roshar, hogwarts);
            addTerritoriesNeighbours(mordor, hogwarts);

            territories.add(narnia);
            territories.add(midkemia);
            territories.add(oz);
            territories.add(gondor);
            territories.add(elantris);
            territories.add(scadrial);
            territories.add(roshar);
            territories.add(hogwarts);
            territories.add(mordor);
        }
    }

}
