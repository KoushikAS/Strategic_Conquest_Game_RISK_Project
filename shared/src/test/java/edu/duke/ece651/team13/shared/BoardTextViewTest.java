package edu.duke.ece651.team13.shared;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoardTextViewTest {
    private V1Map map;
    private BoardTextView view;
    @BeforeEach
    public void setUp() {
        map = new V1Map(10);
        view = new BoardTextView(map);
    }

    @Test
    public void testDisplayOneTerritory() {
        Territory t = new GameTerritory("Narnia");
        String expected = "0 units in Narnia\n";
        String actual = view.displayOneTerritory(t);
        assertEquals(expected, actual);
    }

    @Test
    public void testDisplay() {
        String expected =
                "Blue player:\n" +
                "-------------\n" +
                "0 units in t1\n" +
                "Green player:\n" +
                "-------------\n" +
                "0 units in t2\n" +
                "Red player:\n" +
                "-------------\n" +
                "0 units in t3\n";
        assertEquals(expected, view.display());
    }
}
