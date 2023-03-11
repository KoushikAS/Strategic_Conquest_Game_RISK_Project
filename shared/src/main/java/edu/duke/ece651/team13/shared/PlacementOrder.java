package edu.duke.ece651.team13.shared;

public interface PlacementOrder {
    /**
     * This method executes the order of placing units into a territory
     */
    void execute();

    /**
     * This method validates the placement order
     *
     * @return null if success and a description of what is wrong otherwise
     */
    String validateOrder();

    /**
     * This getter methods gets the total units available to place into remaining territories
     *
     * @return the total units available to assign to territories
     */
    int getTotalUnits();
}
