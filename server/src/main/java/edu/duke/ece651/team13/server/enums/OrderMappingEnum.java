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
    DONE("DONE");

    private final String value;

    public static OrderMappingEnum findByValue(String value) {
        for (OrderMappingEnum order : values()) {
            if (order.getValue().equals(value)) {
                return order;
            }
        }
        throw new IllegalArgumentException("The Order Type mentioned is invalid");
    }
}