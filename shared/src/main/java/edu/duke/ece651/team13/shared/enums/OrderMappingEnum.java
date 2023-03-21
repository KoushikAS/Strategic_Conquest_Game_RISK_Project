package edu.duke.ece651.team13.shared.enums;


/**
 * This class contains enum for mapping between different types of orders
 */
public enum OrderMappingEnum {
    MOVE("M"),
    ATTACK("A"),
    DONE("D"),
    INITIALISE("I");

    private final String value;
    OrderMappingEnum(String value) {
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
        throw new IllegalArgumentException("The Order Type mentioned is invalid");
    }
}