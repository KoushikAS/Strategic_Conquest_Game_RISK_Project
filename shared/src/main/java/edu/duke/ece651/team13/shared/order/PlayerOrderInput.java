package edu.duke.ece651.team13.shared.order;

import edu.duke.ece651.team13.shared.enums.OrderMappingEnum;

/**
 * This class handles the information of one human player
 */
public class PlayerOrderInput implements PlayerOrderInputRO {

    private OrderMappingEnum orderType;
    private String source;
    private String destination;
    private int units;

    /**
     * Construct a new Game Round
     */
    public PlayerOrderInput(OrderMappingEnum orderType, String source, String destination, int units) {
        this.orderType = orderType;
        this.source = source;
        this.destination = destination;
        this.units = units;
    }

    public OrderMappingEnum getOrderType() {
        return orderType;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public int getUnits() {
        return units;
    }

}
