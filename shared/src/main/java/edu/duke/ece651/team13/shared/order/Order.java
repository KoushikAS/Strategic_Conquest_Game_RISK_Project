package edu.duke.ece651.team13.shared.order;

import edu.duke.ece651.team13.shared.PlayerRO;
import edu.duke.ece651.team13.shared.territory.Territory;
import edu.duke.ece651.team13.shared.map.MapRO;
import edu.duke.ece651.team13.shared.rulechecker.RuleChecker;

public abstract class Order {
    protected final RuleChecker orderRuleChecker;
    protected PlayerRO player;
    protected Territory source;
    protected Territory destination;
    protected int units;

    protected Order(RuleChecker orderRuleChecker,
                    PlayerRO player,
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
        Order newOrder = getOrderOnNewMap(map);
        newOrder.act();
    }

    /**
     * Validate the order
     */
    public abstract String validate();

    /**
     * Helper function to get the order on a new map
     *   (Match the source and destination territories by name)
     */
    protected Order getOrderOnNewMap(MapRO map) {
        return null;
    }

    /**
     * Validate the order on a specified map
     * (would try to get the src and dst territory by id in the specified map, if those ids don't exist,
     * throws IllegalArgumentException)
     */
    public String validateOnMap(MapRO map) {
        Order newOrder = getOrderOnNewMap(map);
        return newOrder.validate();
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

    public PlayerRO getPlayer() {
        return player;
    }
}
