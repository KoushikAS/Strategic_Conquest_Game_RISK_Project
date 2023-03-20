package edu.duke.ece651.team13.server.order;

import edu.duke.ece651.team13.server.rulechecker.MoveOwnershipChecker;
import edu.duke.ece651.team13.server.rulechecker.MovePathChecker;
import edu.duke.ece651.team13.server.rulechecker.MoveUnitNumChecker;
import edu.duke.ece651.team13.server.rulechecker.RuleChecker;
import edu.duke.ece651.team13.shared.map.MapRO;
import edu.duke.ece651.team13.shared.map.V1Map12Territories;
import edu.duke.ece651.team13.shared.player.HumanPlayer;
import edu.duke.ece651.team13.shared.player.Player;
import edu.duke.ece651.team13.shared.territory.GameTerritory;
import edu.duke.ece651.team13.shared.territory.Territory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class MoveOrderTest {


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

        Order order1 = new MoveOrder(
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
        RuleChecker ownershipChecker = new MoveOwnershipChecker(null);
        Player tom = new HumanPlayer("Tom Riddle");
        Territory hogwarts = new GameTerritory("Hogwarts");
        hogwarts.setOwner(tom);
        hogwarts.setUnitNum(10);
        Territory alley = new GameTerritory("Diagon Alley");
        alley.setOwner(tom);
        Order move = new MoveOrder(ownershipChecker,
                tom, hogwarts, alley, 0);
        move.act();
        assertEquals(10, hogwarts.getUnitNum());

        Order move2 = new MoveOrder(ownershipChecker,
                tom, hogwarts, alley, 5);
        move2.act();
        assertEquals(5, hogwarts.getUnitNum());
        assertEquals(5, alley.getUnitNum());
    }

    @Test
    void test_ownershipChecker() {
        RuleChecker ownershipChecker = new MoveOwnershipChecker(null);
        Player tom = new HumanPlayer("Tom Riddle");
        Territory hogwarts = new GameTerritory("Hogwarts");
        hogwarts.setOwner(tom);
        Territory alley = new GameTerritory("Diagon Alley");

        Order move = new MoveOrder(ownershipChecker,
                tom, hogwarts, alley, 0);
        assertEquals("Invalid move order: The destination territory is not owned by you.",
                move.validate());

        Order move2 = new MoveOrder(ownershipChecker,
                tom, alley, hogwarts, 0);
        assertEquals("Invalid move order: The source territory is not owned by you.",
                move2.validate());

        alley.setOwner(tom);
        assertNull(move.validate());
        assertNull(move2.validate());
    }

    @Test
    void test_unitnumChecker(){
        RuleChecker unitnumChecker = new MoveUnitNumChecker(null);
        Player dobby = new HumanPlayer("Dobby");
        Territory socks = new GameTerritory("Socks");
        Territory labor = new GameTerritory("labor");
        socks.setUnitNum(10);

        Order move_validnum = new MoveOrder(unitnumChecker, dobby, socks, labor, 10);
        assertNull(move_validnum.validate());

        Order move_insufficient_unit = new MoveOrder(unitnumChecker, dobby, socks, labor, 11);
        assertEquals("Invalid move order: Don't have sufficient unit number in the territory.",
                move_insufficient_unit.validate());

        Order move_negativenum = new MoveOrder(unitnumChecker, dobby, socks, labor, -1);
        assertEquals("Invalid move order: The unit number to move should be >= 0.",
                move_negativenum.validate());
    }

    /**
     * Helper function to construct list of territories for testing
     */
    private ArrayList<Territory> getTerritories(){
        ArrayList<Territory> territories = new ArrayList<>();

        Territory narnia = new GameTerritory("Narnia");
        Territory midkemia = new GameTerritory("Midkemia");
        Territory oz = new GameTerritory("Oz");
        Territory gondor = new GameTerritory("Gondor");
        Territory elantris = new GameTerritory("Elantris");
        Territory scadrial = new GameTerritory("Scadrial");
        Territory roshar = new GameTerritory("Roshar");
        Territory hogwarts = new GameTerritory("Hogwarts");
        Territory mordor = new GameTerritory("Mordor");

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

        territories.add(narnia);
        territories.add(midkemia);
        territories.add(oz);
        territories.add(gondor);
        territories.add(elantris);
        territories.add(scadrial);
        territories.add(roshar);
        territories.add(hogwarts);
        territories.add(mordor);

        return territories;
    }

    /**
     * Helper function to add neighboring relationship
     */
    private void addTerritoriesNeighbours(Territory t1, Territory t2) {
        t1.addNeighbours(t2);
        t2.addNeighbours(t1);
    }

    @Test
    void test_pathChecker(){
        RuleChecker pathChecker = new MovePathChecker(null);
        ArrayList<Territory> territories = getTerritories();

        // Mickey has valid path from narnia(0) to oz(2) through midkemia(1)
        Player mickey = new HumanPlayer("Mickey");
        for(int i = 0; i < 3; i++){
            territories.get(i).setOwner(mickey);
        }
        Order validPath = new MoveOrder(pathChecker, mickey, territories.get(0), territories.get(2), 0);
        assertNull(validPath.validate());

        // Donald owns gondor(3), elantris(4), scadrial(5), roshar(6)
        Player donald = new HumanPlayer("Donald");
        for(int i = 3; i < 7; i++){
            territories.get(i).setOwner(donald);
        }
        // Minnie owns Mordor(8)
        Player minnie = new HumanPlayer("Minnie");
        territories.get(8).setOwner(minnie);

        // Mickey owns Hogwarts(7)
        territories.get(7).setOwner(mickey);
        // Mickey doesn't have valid path to hogwarts
        Order noPath1 = new MoveOrder(pathChecker, mickey, territories.get(0), territories.get(7), 0);
        assertEquals("Invalid move order: There is not a valid path between the src and dst.",
                noPath1.validate());
        Order noPath2 = new MoveOrder(pathChecker, mickey, territories.get(2), territories.get(7), 0);
        assertEquals("Invalid move order: There is not a valid path between the src and dst.",
                noPath2.validate());

        // Donald cannot reach Gondor(3) from Elantris(4)
        Order noPath3 = new MoveOrder(pathChecker, donald, territories.get(4), territories.get(3), 0);
        assertEquals("Invalid move order: There is not a valid path between the src and dst.",
                noPath3.validate());
        // Donald can reach Roshar(6) from Elantris(4)
        Order valid1 = new MoveOrder(pathChecker, donald, territories.get(4), territories.get(6), 0);
        assertNull(valid1.validate());
    }

    @Test
    void test_ruleChain(){
        RuleChecker pathChecker = new MovePathChecker(null);
        RuleChecker unitnumChecker = new MoveUnitNumChecker(pathChecker);
        RuleChecker ownershipChecker = new MoveOwnershipChecker(unitnumChecker);

        Player dobby = new HumanPlayer("Dobby");
        Player harry = new HumanPlayer("Harry Potter");
        Territory socks = new GameTerritory("Socks");
        Territory labor = new GameTerritory("labor");
        Territory gringotts = new GameTerritory("Gringotts");

        socks.setUnitNum(10);
        socks.setOwner(dobby);
        labor.setUnitNum(5);
        labor.setOwner(dobby);
        gringotts.setOwner(harry);
        gringotts.setUnitNum(10);

        Order noPath1 = new MoveOrder(pathChecker, dobby, socks, labor, 10);
        assertEquals("Invalid move order: There is not a valid path between the src and dst.",
                noPath1.validate());

        Order insufficient1 = new MoveOrder(ownershipChecker, dobby, socks, labor, 20);
        assertEquals("Invalid move order: Don't have sufficient unit number in the territory.",
                insufficient1.validate());

        Order ownership1 = new MoveOrder(ownershipChecker, harry, gringotts, labor, 10);
        assertEquals("Invalid move order: The destination territory is not owned by you.",
                ownership1.validate());

        socks.addNeighbours(labor);
        labor.addNeighbours(socks);

        Order valid1 = new MoveOrder(ownershipChecker, dobby, socks, labor, 10);
        assertNull(valid1.validate());
    }
}
