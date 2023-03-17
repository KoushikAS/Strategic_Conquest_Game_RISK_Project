package edu.duke.ece651.team13.shared.order;

import edu.duke.ece651.team13.shared.Player;
import edu.duke.ece651.team13.shared.PlayerRO;
import edu.duke.ece651.team13.shared.territory.Territory;
import edu.duke.ece651.team13.shared.map.MapRO;
import edu.duke.ece651.team13.shared.rulechecker.MoveOwnershipChecker;
import edu.duke.ece651.team13.shared.rulechecker.MovePathChecker;
import edu.duke.ece651.team13.shared.rulechecker.MoveUnitNumChecker;
import edu.duke.ece651.team13.shared.rulechecker.RuleChecker;

/**
 * Move order
 */
public class MoveOrder extends Order {
    public MoveOrder(RuleChecker ruleChecker,
                     PlayerRO player,
                     Territory source,
                     Territory destination,
                     int units){
        super(ruleChecker, player, source, destination, units);
    }

    /**
     * Construct a MoveOrder with default rulechecker
     *    MoveOwnershipChecker -> MoveUnitNumChecker -> MovePathChecker
     */
    public MoveOrder(PlayerRO player,
                     Territory source,
                     Territory destination,
                     int units){
        this(getDefaultRuleChecker(), player, source, destination, units);
    }

    /**
     * Get the default rule checker chain
     *     MoveOwnershipChecker -> MoveUnitNumChecker -> MovePathChecker
     */
    private static RuleChecker getDefaultRuleChecker(){
        RuleChecker pathChecker = new MovePathChecker(null);
        RuleChecker unitnumChecker = new MoveUnitNumChecker(pathChecker);
        return new MoveOwnershipChecker(unitnumChecker);
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

    @Override
    protected MoveOrder getOrderOnNewMap(MapRO map) {
        Territory newSource = map.getTerritoryByName(source.getName());
        Territory newDestination = map.getTerritoryByName(destination.getName());
        return new MoveOrder(this.orderRuleChecker, this.player, newSource, newDestination, this.units);
    }
}
