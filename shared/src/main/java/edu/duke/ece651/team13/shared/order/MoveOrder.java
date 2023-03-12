package edu.duke.ece651.team13.shared.order;

import edu.duke.ece651.team13.shared.Player;
import edu.duke.ece651.team13.shared.territory.Territory;
import edu.duke.ece651.team13.shared.map.MapRO;
import edu.duke.ece651.team13.shared.rulechecker.RuleChecker;

/**
 * Move order
 */
public class MoveOrder extends PlayerOrder {
    public MoveOrder(RuleChecker ruleChecker,
                     Player player,
                     Territory source,
                     Territory destination,
                     int units){
        super(ruleChecker, player, source, destination, units);
    }

    @Override
    public void act() {
        int sourceUnitNum = source.getUnitNum() - units;
        source.setUnitNum(sourceUnitNum);
        int destUnitNum = destination.getUnitNum() + units;
        destination.setUnitNum(destUnitNum);
    }

    @Override
    public String validate() {
        return orderRuleChecker.checkOrder(this);
    }

    private MoveOrder getOrderOnNewMap(MapRO map) {
        Territory newSource = map.getTerritoryByID(source.getId());
        Territory newDestination = map.getTerritoryByID(destination.getId());
        return new MoveOrder(this.orderRuleChecker, this.player, newSource, newDestination, this.units);
    }

    @Override
    public String validateOnMap(MapRO map){
        MoveOrder newOrder = getOrderOnNewMap(map);
        return newOrder.validate();
    }

    @Override
    public void actOnMap(MapRO map){
        MoveOrder newOrder = getOrderOnNewMap(map);
        newOrder.act();
    }
}
