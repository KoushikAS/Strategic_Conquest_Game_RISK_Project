package edu.duke.ece651.team13.server.order;

import edu.duke.ece651.team13.server.rulechecker.AttackOwnershipChecker;
import edu.duke.ece651.team13.server.rulechecker.AttackPathChecker;
import edu.duke.ece651.team13.server.rulechecker.AttackUnitNumChecker;
import edu.duke.ece651.team13.server.rulechecker.RuleChecker;
import edu.duke.ece651.team13.shared.map.MapRO;
import edu.duke.ece651.team13.shared.map.V1Map12Territories;
import edu.duke.ece651.team13.shared.player.HumanPlayer;
import edu.duke.ece651.team13.shared.player.Player;
import edu.duke.ece651.team13.shared.player.PlayerRO;
import edu.duke.ece651.team13.shared.territory.GameTerritory;
import edu.duke.ece651.team13.shared.territory.Territory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class AttackOrderTest {


    AutoCloseable openMocks;

    @BeforeEach
    void setUp() {
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
                new HumanPlayer("Green"),
                map1.getTerritoryByName("Boxer"), map1.getTerritoryByName("Poodle"), 0);
        Order order2 = order1.getOrderOnNewMap(map2);
        assertEquals("Boxer", order2.getSource().getName());
        assertEquals("Poodle", order2.getDestination().getName());

        order2.getSource().setUnitNum(100);
        order2.getDestination().setUnitNum(200);
        assertEquals(10, order1.getSource().getUnitNum());
        assertEquals(10, order1.getDestination().getUnitNum());
    }

    @Test
    void test_act() {
        MapRO map1 = new V1Map12Territories(10);
        Territory boxer = map1.getTerritoryByName("Boxer");
        Territory poodle = map1.getTerritoryByName("Poodle");
        Player green = new HumanPlayer("Green");
        boxer.setUnitNum(10);
        Order order1 = new AttackOrder(
                null, green, boxer, poodle, 10);
        order1.act();
        HashMap<PlayerRO, Integer> attackers = poodle.getAttackers();
        assertEquals(10, attackers.get(green));
        assertEquals(0, boxer.getUnitNum());
    }

    @Test
    void test_actOnMap() {
        MapRO map1 = new V1Map12Territories(10);

        Territory boxer = map1.getTerritoryByName("Boxer");
        Territory poodle = map1.getTerritoryByName("Poodle");
        Player green = new HumanPlayer("Green");

        boxer.setUnitNum(10);
        MapRO map2 = map1.replicate();
        Territory cloneBoxer = map2.getTerritoryByName("Boxer");
        Territory clonePoodle = map2.getTerritoryByName("Poodle");

        Order order1 = new AttackOrder(
                null, green, boxer, poodle, 10);
        order1.actOnMap(map2);
        HashMap<PlayerRO, Integer> attackers = poodle.getAttackers();
        assertTrue(attackers.isEmpty());
        assertEquals(10, boxer.getUnitNum());
        HashMap<PlayerRO, Integer> cloneAttackers = clonePoodle.getAttackers();
        assertEquals(10, cloneAttackers.get(green));
        assertEquals(0, cloneBoxer.getUnitNum());
    }

    @Test
    void test_ownershipChecker() {
        RuleChecker ownershipChecker = new AttackOwnershipChecker(null);
        Player tom = new HumanPlayer("Tom Riddle");
        Player oliver = new HumanPlayer("Oliver Chen");
        Territory hogwarts = new GameTerritory("Hogwarts");
        Territory alley = new GameTerritory("Diagon Alley");
        hogwarts.setOwner(tom);
        alley.setOwner(tom);

        Order attack = new AttackOrder(ownershipChecker,
                tom, hogwarts, alley, 0);
        assertEquals("Invalid attack order: The destination territory cannot be owned by you.",
                attack.validate());

        alley.setOwner(oliver);
        Order attack2 = new AttackOrder(ownershipChecker,
                tom, alley, hogwarts, 0);
        assertEquals("Invalid attack order: The source territory is not owned by you.",
                attack2.validate());

        Order attack3 = new AttackOrder(ownershipChecker,
                tom, hogwarts, alley, 0);
        assertNull(attack3.validate());
    }

    @Test
    void test_unitnumChecker() {
        RuleChecker unitnumChecker = new AttackUnitNumChecker(null);
        Player dobby = new HumanPlayer("Dobby");
        Territory socks = new GameTerritory("Socks");
        Territory labor = new GameTerritory("labor");
        socks.setUnitNum(10);

        Order attack_validnum1 = new MoveOrder(unitnumChecker, dobby, socks, labor, 10);
        assertNull(attack_validnum1.validate());

        Order attack_validnum2 = new MoveOrder(unitnumChecker, dobby, socks, labor, 9);
        assertNull(attack_validnum2.validate());

        Order attack_insufficient_unit = new MoveOrder(unitnumChecker, dobby, socks, labor, 11);
        assertEquals("Invalid attack order: Don't have sufficient unit number in the territory.",
                attack_insufficient_unit.validate());

        Order attack_negativenum = new MoveOrder(unitnumChecker, dobby, socks, labor, -1);
        assertEquals("Invalid attack order: The unit number to move should be >= 0.",
                attack_negativenum.validate());
    }

    @Test
    void test_pathChecker() {
        RuleChecker pathChecker = new AttackPathChecker(null);
        RuleChecker unitnumChecker = new AttackUnitNumChecker(pathChecker);
        RuleChecker ownershipChecker = new AttackOwnershipChecker(unitnumChecker);

        Player green = new HumanPlayer("Green Player");
        Player red = new HumanPlayer("red Player");
        Player purple = new HumanPlayer("purple Player");

        ArrayList<Territory> territories = getTerritories();
        for (int i = 0; i < 3; i++) {
            territories.get(i).setOwner(green);
        }
        for (int i = 3; i < 6; i++) {
            territories.get(i).setOwner(red);
        }
        for (int i = 6; i < 9; i++) {
            territories.get(i).setOwner(purple);
        }

        Territory narnia = territories.get(0);
        Territory midkemia = territories.get(1);
        Territory oz = territories.get(2);
        Territory gondor = territories.get(3);
        Territory mordor = territories.get(4);
        Territory hogwarts = territories.get(5);
        Territory elantris = territories.get(6);
        Territory scadrial = territories.get(7);
        Territory roshar = territories.get(8);

        // invalid attacks (previous checkers)
        Order attack_invalid1 = new AttackOrder(ownershipChecker, red, hogwarts, roshar, 4);
        Order attack_invalid2 = new AttackOrder(ownershipChecker, red, hogwarts, roshar, -1);
        Order attack_invalid3 = new AttackOrder(ownershipChecker, purple, narnia, elantris, 3);
        Order attack_invalid4 = new AttackOrder(ownershipChecker, purple, elantris, scadrial, 5);
        assertEquals("Invalid attack order: Don't have sufficient unit number in the territory.", attack_invalid1.validate());
        assertEquals("Invalid attack order: The unit number to move should be >= 0.", attack_invalid2.validate());
        assertEquals("Invalid attack order: The source territory is not owned by you.", attack_invalid3.validate());
        assertEquals("Invalid attack order: The destination territory cannot be owned by you.", attack_invalid4.validate());

        // invalid attacks (path/neighbor checker)
        Order attack_invalid5 = new AttackOrder(ownershipChecker, purple, elantris, mordor, 5);
        assertEquals("Invalid attack order: You can only attack an adjacent territory owned by another player.", attack_invalid5.validate());

        // valid attacks
        Order attack_valid1 = new AttackOrder(ownershipChecker, green, oz, gondor, 7);
        Order attack_valid2 = new AttackOrder(ownershipChecker, green, oz, mordor, 8);
        Order attack_valid3 = new AttackOrder(ownershipChecker, green, midkemia, scadrial, 11);
        Order attack_valid4 = new AttackOrder(ownershipChecker, purple, elantris, narnia, 6);
        Order attack_valid5 = new AttackOrder(ownershipChecker, red, hogwarts, roshar, 3);
        assertNull(attack_valid1.validate());
        assertNull(attack_valid2.validate());
        assertNull(attack_valid3.validate());
        assertNull(attack_valid4.validate());
        assertNull(attack_valid5.validate());
    }

    /**
     * Helper function to construct list of territories for testing
     */
    private ArrayList<Territory> getTerritories() {
        ArrayList<Territory> territories = new ArrayList<>();

        Territory narnia = new GameTerritory("Narnia");
        Territory midkemia = new GameTerritory("Midkemia");
        Territory oz = new GameTerritory("Oz");
        Territory gondor = new GameTerritory("Gondor");
        Territory mordor = new GameTerritory("Mordor");
        Territory hogwarts = new GameTerritory("Hogwarts");
        Territory elantris = new GameTerritory("Elantris");
        Territory scadrial = new GameTerritory("Scadrial");
        Territory roshar = new GameTerritory("Roshar");

        narnia.setUnitNum(10);
        midkemia.setUnitNum(12);
        oz.setUnitNum(8);
        gondor.setUnitNum(13);
        mordor.setUnitNum(14);
        hogwarts.setUnitNum(3);
        elantris.setUnitNum(6);
        scadrial.setUnitNum(5);
        roshar.setUnitNum(3);

        addTerritoriesNeighbours(narnia, midkemia);
        addTerritoriesNeighbours(narnia, elantris);
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

        // green
        territories.add(narnia);
        territories.add(midkemia);
        territories.add(oz);
        // red
        territories.add(gondor);
        territories.add(mordor);
        territories.add(hogwarts);
        // purple
        territories.add(elantris);
        territories.add(scadrial);
        territories.add(roshar);

        return territories;
    }

    /**
     * Helper function to add neighboring relationship
     */
    private void addTerritoriesNeighbours(Territory t1, Territory t2) {
        t1.addNeighbours(t2);
        t2.addNeighbours(t1);
    }
}