package edu.duke.ece651.team13.shared.order;

import edu.duke.ece651.team13.shared.enums.OrderMappingEnum;

import java.io.Serializable;
import java.util.Objects;

/**
 * This class handles the information of one human player
 */
public class PlayerOrderInput implements PlayerOrderInputRO, Serializable {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerOrderInput that = (PlayerOrderInput) o;
        return units == that.units && orderType == that.orderType && Objects.equals(source, that.source) && Objects.equals(destination, that.destination);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderType, source, destination, units);
    }
}
