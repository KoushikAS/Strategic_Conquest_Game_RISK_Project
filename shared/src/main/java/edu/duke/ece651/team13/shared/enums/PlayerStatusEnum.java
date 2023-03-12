package edu.duke.ece651.team13.shared.enums;


/**
 * This class contains enum of player
 */
public enum PlayerStatusEnum {
    LOSE("LOOSE"),
    PLAYING("PLAYING");

    private final String value;

    private PlayerStatusEnum(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}