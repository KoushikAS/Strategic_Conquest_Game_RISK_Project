package edu.duke.ece651.team13.server;

import java.util.Random;

/**
 * This class represents the dice
 */
public class Dice {
    private final int minValue;
    private final int maxValue;

    public Dice(int minValue, int maxValue) {
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    /**
     * Roll the dice
     * @return  the result ranged in minvalue-maxvalue, included
     */
    public int roll(){
        Random rand = new Random();
        return rand.nextInt((maxValue - minValue) + 1) + minValue;
    }
}
