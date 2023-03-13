package edu.duke.ece651.team13.shared.order;

import edu.duke.ece651.team13.shared.Player;
import edu.duke.ece651.team13.shared.map.MapRO;
import edu.duke.ece651.team13.shared.rulechecker.RuleChecker;
import edu.duke.ece651.team13.shared.territory.Territory;

public class AttackOrder extends PlayerOrder{
    protected AttackOrder(RuleChecker orderRuleChecker, Player player, Territory source, Territory destination, int units) {
        super(orderRuleChecker, player, source, destination, units);
    }

    @Override
    protected PlayerOrder getOrderOnNewMap(MapRO map){
        Territory newSource = map.getTerritoryByName(source.getName());
        Territory newDestination = map.getTerritoryByName(destination.getName());
        return new AttackOrder(this.orderRuleChecker, this.player, newSource, newDestination, this.units);
    }

    @Override
    public void act() {
        int sourceUnitNum = source.getUnitNum() - units;
        source.setUnitNum(sourceUnitNum);
        destination.addAttacker(player, units);
    }

    @Override
    public String validate() {
        return orderRuleChecker.checkOrder(this);
    }
}
