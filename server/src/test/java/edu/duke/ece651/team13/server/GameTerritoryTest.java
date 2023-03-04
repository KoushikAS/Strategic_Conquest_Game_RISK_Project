package edu.duke.ece651.team13.server;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTerritoryTest {

    @Test
    void testGetId() {
        Territory t = new GameTerritory(1, "testTerritory");
        assertEquals(1, t.getId());
    }

    @Test
    void testGetName() {
        String name = "testTerritory";
        Territory t = new GameTerritory(1, name);
        assertEquals(name, t.getName());
    }

    @Test
    void test_get_setUnitNum() {
        String name = "testTerritory";
        Territory t = new GameTerritory(1, name);
        assertEquals(0, t.getUnitNum());
        t.setUnitNum(5);
        assertEquals(5, t.getUnitNum());
    }

    @Test
    void test_setUnitNum_illegal(){
        String name = "testTerritory";
        Territory t = new GameTerritory(1, name);
        assertThrows(IllegalArgumentException.class, () -> t.setUnitNum(-1));
    }

    @Test
    void test_get_setTempUnitNum() {
        String name = "testTerritory";
        Territory t = new GameTerritory(1, name);
        assertEquals(0, t.getTempUnitNum());
        t.setTempUnitNum(5);
        assertEquals(5, t.getTempUnitNum());
        assertEquals(0, t.getUnitNum());
    }

    @Test
    void test_setTempUnitNum_illegal(){
        String name = "testTerritory";
        Territory t = new GameTerritory(1, name);
        assertThrows(IllegalArgumentException.class, () -> t.setTempUnitNum(-1));
    }

    @Test
    void test_rollback_commitTempUnitNum() {
        String name = "testTerritory";
        Territory t = new GameTerritory(1, name);
        t.setTempUnitNum(5);
        assertEquals(5, t.getTempUnitNum());
        assertEquals(0, t.getUnitNum());
        t.commitTempUnitNum();
        assertEquals(5, t.getUnitNum());
        t.setTempUnitNum(10);
        assertEquals(10, t.getTempUnitNum());
        t.rollbackTempUnitNum();
        assertEquals(5, t.getTempUnitNum());
    }

    @Test
    void test_add_getNeighbors() {
        String name = "testTerritory";
        Territory t = new GameTerritory(1, name);
        assertTrue(t.getNeighbors().isEmpty());

        Territory neighbor = new GameTerritory(2, name);
        t.addNeighbor(neighbor);
        assertTrue(t.getNeighbors().contains(neighbor));
    }

    @Test
    void test_get_setOwner() {
        Player player = new HumanPlayer(1, "testPlayer");
        String name = "testTerritory";
        Territory t = new GameTerritory(1, name);
        assertNull(t.getOwner());
        t.setOwner(player);
        assertEquals(player, t.getOwner());
    }

    @Test
    void test_get_add_clearAttacker() {
        String name = "testTerritory";
        Territory t = new GameTerritory(1, name);
        assertTrue(t.getAttackers().isEmpty());

        Player attacker1 = new HumanPlayer(1, "attacker1");
        Player attacker2 = new HumanPlayer(2, "attacker2");
        t.addAttacker(attacker1, 10);
        t.addAttacker(attacker2, 8);
        assertEquals(10, t.getAttackers().get(attacker1));
        assertEquals(8, t.getAttackers().get(attacker2));

        t.clearAttackers();
        assertTrue(t.getAttackers().isEmpty());
    }
}