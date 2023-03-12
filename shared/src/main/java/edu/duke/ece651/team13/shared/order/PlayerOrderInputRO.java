package edu.duke.ece651.team13.shared.order;

import edu.duke.ece651.team13.shared.enums.OrderMappingEnum;

/**
 * The interface for each round of the game
 */
public interface PlayerOrderInputRO {

    public OrderMappingEnum getOrderType();

    public String getSource();

    public String getDestination();

    public int getUnits();

}
