package edu.duke.ece651.team13.shared;

public abstract class PlayerOrder {
    protected final RuleChecker orderRuleChecker;
    protected Player player;
    protected Territory source;
    protected Territory destination;
    protected int units;

    protected PlayerOrder(RuleChecker orderRuleChecker,
                          Player player,
                          Territory source,
                          Territory destination,
                          int units) {
        this.orderRuleChecker = orderRuleChecker;
        this.player = player;
        this.source = source;
        this.destination = destination;
        this.units = units;
    }

    public abstract void act();

    public abstract String validate();

    public Territory getSource() {
        return source;
    }

    public Territory getDestination() {
        return destination;
    }

    public int getUnits() {
        return units;
    }
}
