package edu.duke.ece651.team13.shared;

import edu.duke.ece651.team13.shared.order.Order;
import edu.duke.ece651.team13.shared.rulechecker.RuleChecker;
import edu.duke.ece651.team13.shared.territory.Territory;

public class PlacementOrderAdapter extends Order implements PlacementOrder {

    private int totalUnits;

    public PlacementOrderAdapter(RuleChecker orderRuleChecker,
                                 Player player,
                                 Territory destination,
                                 int units,
                                 int totalUnits) {
        super(orderRuleChecker, player, null, destination, units);
        this.totalUnits = totalUnits;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute() {
        act();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String validateOrder() {
        return validate();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getTotalUnits() {
        return totalUnits;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void act() {
        destination.setUnitNum(units);
        totalUnits -= units;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String validate() {
        return orderRuleChecker.checkOrder(this);
    }
}
