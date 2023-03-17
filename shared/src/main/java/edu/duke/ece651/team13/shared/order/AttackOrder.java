package edu.duke.ece651.team13.shared.order;

import edu.duke.ece651.team13.shared.player.PlayerRO;
import edu.duke.ece651.team13.shared.map.MapRO;
import edu.duke.ece651.team13.shared.rulechecker.MoveOwnershipChecker;
import edu.duke.ece651.team13.shared.rulechecker.MovePathChecker;
import edu.duke.ece651.team13.shared.rulechecker.MoveUnitNumChecker;
import edu.duke.ece651.team13.shared.rulechecker.RuleChecker;
import edu.duke.ece651.team13.shared.territory.Territory;

public class AttackOrder extends Order{
    public AttackOrder(RuleChecker orderRuleChecker, PlayerRO player, Territory source, Territory destination, int units) {
        super(orderRuleChecker, player, source, destination, units);
    }

    public AttackOrder(PlayerRO player, Territory source, Territory destination, int units) {
        this(getDefaultRuleChecker(), player, source, destination, units);
    }


    @Override
    protected Order getOrderOnNewMap(MapRO map){
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

    //TODO To check the validation of Attack order
    /**
     * Get the default rule checker chain
     *     MoveOwnershipChecker -> MoveUnitNumChecker -> MovePathChecker
     */
    private static RuleChecker getDefaultRuleChecker(){
        RuleChecker pathChecker = new MovePathChecker(null);
        RuleChecker unitnumChecker = new MoveUnitNumChecker(pathChecker);
        return new MoveOwnershipChecker(unitnumChecker);
    }
}
