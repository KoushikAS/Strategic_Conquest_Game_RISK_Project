package edu.duke.ece651.team13.shared.rulechecker;

import edu.duke.ece651.team13.shared.PlacementOrderAdapter;
import edu.duke.ece651.team13.shared.order.PlayerOrder;

public class PlacementChecker extends RuleChecker {
    public PlacementChecker(RuleChecker next) {
        super(next);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String checkMyRule(PlayerOrder order) {
        PlacementOrderAdapter placementOrder = (PlacementOrderAdapter) order;
        if (placementOrder.getUnits() > placementOrder.getTotalUnits()) {
            return "Invalid unit placement: You cannot place more units that you have";
        }
        return null;
    }
}
