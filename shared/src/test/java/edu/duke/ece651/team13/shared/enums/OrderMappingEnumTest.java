package edu.duke.ece651.team13.shared.enums;

import org.junit.jupiter.api.Test;

import static edu.duke.ece651.team13.shared.enums.OrderMappingEnum.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderMappingEnumTest {

    @Test
    public void getValue_test(){
        assertEquals("M", MOVE.getValue());
        assertEquals("A", ATTACK.getValue());
        assertEquals("D", DONE.getValue());
        assertEquals("I", INITIALISE.getValue());
    }

    @Test
    public void findByValue_test(){
        assertEquals(MOVE, findByValue("M"));
        assertEquals(ATTACK, findByValue("A"));
        assertEquals(DONE, findByValue("D"));
        assertEquals(INITIALISE, findByValue("I"));
    }
}
