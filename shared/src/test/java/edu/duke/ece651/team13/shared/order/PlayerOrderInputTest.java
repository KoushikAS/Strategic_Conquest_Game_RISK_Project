package edu.duke.ece651.team13.shared.order;

import edu.duke.ece651.team13.shared.enums.OrderMappingEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class PlayerOrderInputTest {

    private PlayerOrderInput poi;

    @BeforeEach
    public void setUp() {
        poi = new PlayerOrderInput(OrderMappingEnum.MOVE, "Narnia", "Midkemia", 10);
    }

    @Test
    public void test_getOrderType() {
        assertEquals(OrderMappingEnum.MOVE, poi.getOrderType());
    }

    @Test
    public void test_getSource() {
        assertEquals("Narnia", poi.getSource());
    }

    @Test
    public void test_getDestination() {
        assertEquals("Midkemia", poi.getDestination());
    }

    @Test
    public void test_getUnits() {
        assertEquals(10, poi.getUnits());
    }

    @Test
    public void test_equals() {
        PlayerOrderInput poi2 = new PlayerOrderInput(OrderMappingEnum.MOVE, "Narnia", "Midkemia", 10);
        assertEquals(poi, poi2);

        // BVA cases
        PlayerOrderInput poi3 = new PlayerOrderInput(OrderMappingEnum.ATTACK, "Narnia", "Midkemia", 10);
        PlayerOrderInput poi4 = new PlayerOrderInput(OrderMappingEnum.MOVE, "Oz", "Midkemia", 10);
        PlayerOrderInput poi5 = new PlayerOrderInput(OrderMappingEnum.MOVE, "Narnia", "Oz", 10);
        PlayerOrderInput poi6 = new PlayerOrderInput(OrderMappingEnum.MOVE, "Narnia", "Midkemia", 100);
        assertNotEquals(poi, poi3);
        assertNotEquals(poi, poi4);
        assertNotEquals(poi, poi5);
        assertNotEquals(poi, poi6);
    }

    @Test
    public void test_hashCode() {
        PlayerOrderInput poi2 = new PlayerOrderInput(OrderMappingEnum.MOVE, "Narnia", "Midkemia", 10);
        assertEquals(poi.hashCode(), poi2.hashCode());
    }
}
