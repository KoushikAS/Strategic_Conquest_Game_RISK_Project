package edu.duke.ece651.team13.shared.enums;


/**
 * This class contains enum of player
 */
public enum OrderMappingEnum {
    MOVE("M"),
    ATTACK("A"),
    DONE("D");

    private final String value;

    private OrderMappingEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static OrderMappingEnum findByValue(String value) {
        for (OrderMappingEnum order : values()) {
            if (order.getValue().equals(value)) {
                return order;
            }
        }
        throw new IllegalArgumentException("The order Type mentioned is invalid");
    }
}