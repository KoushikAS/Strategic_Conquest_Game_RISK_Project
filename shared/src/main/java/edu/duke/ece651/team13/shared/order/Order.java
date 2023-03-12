package edu.duke.ece651.team13.shared.order;

import edu.duke.ece651.team13.shared.Player;
import edu.duke.ece651.team13.shared.territory.Territory;
import edu.duke.ece651.team13.shared.map.MapRO;
import edu.duke.ece651.team13.shared.rulechecker.RuleChecker;

public abstract class Order {
    protected final RuleChecker orderRuleChecker;
    protected Player player;
    protected Territory source;
    protected Territory destination;
    protected int units;

    protected Order(RuleChecker orderRuleChecker,
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

    /**
     * Execute the order
     */
    public abstract void act();

    /**
     * Execute the order on the specified source and destination, used in pre-validation
     */
    public void actOnMap(MapRO map) {
    }

    /**
     * Validate the order
     */
    public abstract String validate();

    /**
     * Validate the order on a specified map
     * (would try to get the src and dst territory by id in the specified map, if those ids don't exist,
     * throws IllegalArgumentException)
     */
    public String validateOnMap(MapRO map) {
        return null;
    }

    public Territory getSource() {
        return source;
    }

    public Territory getDestination() {
        return destination;
    }

    public int getUnits() {
        return units;
    }

    public Player getPlayer() {
        return player;
    }
}
