package edu.duke.ece651.team13.shared;

/**
 * This class is a text view representation of the RISC game board
 */
public class BoardTextView implements BoardView {

    private V1Map map;

    /**
     * Constructs a BoardTextView instance
     * @param map
     */
    public BoardTextView(V1Map map) {
        this.map = map;
    }

    /**
     * This method displays the board
     */
    @Override
    public String display() {
        int initialUnit = map.getInitialUnit();
        StringBuffer sb = new StringBuffer();
        sb.append(initialUnit + " units in Territory1 (next to: Territory2)\n");
        sb.append(initialUnit + " units in Territory2 (next to: Territory2)");
        return sb.toString();
    }
}
