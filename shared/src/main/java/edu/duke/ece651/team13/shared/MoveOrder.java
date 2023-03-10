package edu.duke.ece651.team13.shared;

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
}
