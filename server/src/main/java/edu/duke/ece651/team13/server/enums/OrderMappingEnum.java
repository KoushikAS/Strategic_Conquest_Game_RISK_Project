package edu.duke.ece651.team13.server.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * This class contains enum for mapping between different types of orders
 */
@AllArgsConstructor
@Getter
public enum OrderMappingEnum {
    MOVE("MOVE"),
    ATTACK("ATTACK"),
    UNIT_UPGRADE("UNIT_UPGRADE"),
    TECH_RESEARCH("TECH_RESEARCH"),
    DONE("DONE");

    private final String value;

    /**
     * Returns the corresponding OrderMappingEnum value for a given string value.
     * @param value the string value of the enum to be returned.
     * @return the corresponding OrderMappingEnum value.
     * @throws IllegalArgumentException if the given value does not match any of the enum values.
     */
    public static OrderMappingEnum findByValue(String value) {
        for (OrderMappingEnum order : values()) {
            if (order.getValue().equals(value)) {
                return order;
            }
        }
        throw new IllegalArgumentException("The Order Type mentioned is invalid");
    }
}