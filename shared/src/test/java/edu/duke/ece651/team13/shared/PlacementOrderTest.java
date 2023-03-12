package edu.duke.ece651.team13.shared;

import edu.duke.ece651.team13.shared.*;
import edu.duke.ece651.team13.shared.rulechecker.PlacementChecker;
import edu.duke.ece651.team13.shared.rulechecker.RuleChecker;
import org.junit.jupiter.api.Test;

import java.net.Socket;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlacementOrderTest {
    @Test
    public void test_execute() {
        RuleChecker placementChecker = new PlacementChecker(null);
        Player tom = new HumanPlayer("Tom Riddle", new Socket());
        Territory hogwarts = new GameTerritory("Hogwarts");
        hogwarts.setOwner(tom);
        hogwarts.setUnitNum(0);
        PlacementOrderAdapter order = new PlacementOrderAdapter(placementChecker, tom, hogwarts, 10, 100);
        order.execute();
        assertEquals(10, hogwarts.getUnitNum());
        assertEquals(90, order.getTotalUnits());
    }

    @Test
    public void test_validateOrder() {
        RuleChecker placementChecker = new PlacementChecker(null);
        Player tom = new HumanPlayer("Tom Riddle", new Socket());
        Territory hogwarts = new GameTerritory("Hogwarts");
        hogwarts.setOwner(tom);
        hogwarts.setUnitNum(0);
        PlacementOrderAdapter order = new PlacementOrderAdapter(placementChecker, tom, hogwarts, 21, 20);
        String actual = order.validateOrder();
        String expected = "Invalid unit placement: You cannot place more units that you have";
        assertEquals(expected, actual);
    }
}