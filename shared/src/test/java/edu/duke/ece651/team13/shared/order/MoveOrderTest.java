package edu.duke.ece651.team13.shared.order;

import edu.duke.ece651.team13.shared.GameTerritory;
import edu.duke.ece651.team13.shared.HumanPlayer;
import edu.duke.ece651.team13.shared.Player;
import edu.duke.ece651.team13.shared.Territory;
import edu.duke.ece651.team13.shared.order.MoveOrder;
import edu.duke.ece651.team13.shared.order.PlayerOrder;
import edu.duke.ece651.team13.shared.rulechecker.OwnershipChecker;
import edu.duke.ece651.team13.shared.rulechecker.RuleChecker;
import org.junit.jupiter.api.Test;

import java.net.Socket;

import static org.junit.jupiter.api.Assertions.*;

class MoveOrderTest {

    @Test
    void test_act() {
        RuleChecker ownershipChecker = new OwnershipChecker(null);
        Player tom = new HumanPlayer("Tom Riddle", new Socket());
        Territory hogwarts = new GameTerritory("Hogwarts");
        hogwarts.setOwner(tom);
        hogwarts.setUnitNum(10);
        Territory alley = new GameTerritory("Diagon Alley");
        alley.setOwner(tom);
        PlayerOrder move = new MoveOrder(ownershipChecker,
                tom, hogwarts, alley, 0);
        move.act();
        assertEquals(10, hogwarts.getUnitNum());

        PlayerOrder move2 = new MoveOrder(ownershipChecker,
                tom, hogwarts, alley, 5);
        move2.act();
        assertEquals(5, hogwarts.getUnitNum());
        assertEquals(5, alley.getUnitNum());
    }

    @Test
    void test_validateOrder() {
        RuleChecker ownershipChecker = new OwnershipChecker(null);
        Player tom = new HumanPlayer("Tom Riddle", new Socket());
        Territory hogwarts = new GameTerritory("Hogwarts");
        hogwarts.setOwner(tom);
        Territory alley = new GameTerritory("Diagon Alley");

        PlayerOrder move = new MoveOrder(ownershipChecker,
                tom, hogwarts, alley, 0);
        assertEquals("Invalid move order: The destination territory is not owned by you.",
                move.validate());

        PlayerOrder move2 = new MoveOrder(ownershipChecker,
                tom, alley, hogwarts, 0);
        assertEquals("Invalid move order: The source territory is not owned by you.",
                move2.validate());

        alley.setOwner(tom);
        assertNull(move.validate());
        assertNull(move2.validate());
    }
}