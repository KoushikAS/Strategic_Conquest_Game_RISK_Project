package edu.duke.ece651.team13.shared;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoardTextViewTest {
    @Test
    public void testDisplay() {
        V1Map map = new V1Map(10);
        BoardTextView view = new BoardTextView(map);
        String expected =
                "10 units in Territory1 (next to: Territory2)\n" +
                "10 units in Territory2 (next to: Territory2)";
        assertEquals(expected, view.display());
    }
}
